/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hudi.sink.partitioner;

import org.apache.flink.api.common.functions.Partitioner;
import org.apache.hudi.common.model.HoodieKey;
import org.apache.hudi.index.bucket.BucketIdentifier;
import org.apache.hudi.util.StreamerUtil;
import org.apache.hudi.util.bucket.SubTaskAssign;
import org.apache.hudi.util.bucket.SubTaskAssignInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Bucket index input partitioner.
 * The fields to hash can be a subset of the primary key and partition fields.
 *
 * @param <T> The type of obj to hash
 */
public class BucketPartitionIndexPartitioner<T extends HoodieKey> implements Partitioner<T> {

    private static final Logger LOG = LoggerFactory.getLogger(BucketPartitionIndexPartitioner.class);
    private final int bucketNum;
    private final String indexKeyFields;
    private final BucketIndexPartitioner<String> partitioner;
    private transient SubTaskAssign subTaskAssign = null;

    public BucketPartitionIndexPartitioner(int bucketNum, String indexKeyFields) {
        this.bucketNum = bucketNum;
        this.indexKeyFields = indexKeyFields;
        partitioner = new BucketIndexPartitioner(bucketNum, indexKeyFields);
    }

    @Override
    public int partition(HoodieKey key, int totalOfSubTask) {
        if (totalOfSubTask <= bucketNum) {
            return partitioner.partition(key.getRecordKey(), totalOfSubTask);
        }
        int curBucket = BucketIdentifier.getBucketId(key, indexKeyFields, bucketNum);
        if (subTaskAssign == null) {
            load(totalOfSubTask);
        }
        String partitionPath = key.getPartitionPath();
        SubTaskAssignInfo subTaskAssignInfo = subTaskAssign.assignTask(curBucket, Math.abs(partitionPath.hashCode()));

        return subTaskAssignInfo.getTaskId();
    }

    private synchronized void load(int totalOfTask) {
        subTaskAssign = new SubTaskAssign(bucketNum, totalOfTask);
    }
}
