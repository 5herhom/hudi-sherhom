package org.apache.hudi.sink.partitioner;

import org.apache.hudi.common.model.HoodieKey;
import org.apache.hudi.common.util.collection.Pair;
import org.apache.hudi.index.bucket.BucketIdentifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * @author sherhom
 * @date 2022/05/26 16:07
 */
public class TestBucketPartitionIndexPartitioner {
  @Test
  public void testBucketPartitionIndexPartitioner() {
    int bucketNum = 4;
    int subTaskNum = 8;
    int keyNum = 20;
    int partitionsNum = 20;
    testSameBucketPartitionsInSameSubTask(bucketNum, subTaskNum, keyNum, partitionsNum);
  }

  @Test
  public void testBucketPartitionIndexPartitioner01() {
    int bucketNum = 4;
    int subTaskNum = 7;
    int keyNum = 20;
    int partitionsNum = 20;
    testSameBucketPartitionsInSameSubTask(bucketNum, subTaskNum, keyNum, partitionsNum);
  }

  @Test
  public void testBucketPartitionIndexPartitioner02() {
    int keyNum = 40;
    int partitionsNum = 40;
    for (int bucketNum = 1; bucketNum <= 10; bucketNum++) {
      for (int subTaskNum = 1; subTaskNum <= 20; subTaskNum++) {
        testSameBucketPartitionsInSameSubTask(bucketNum, subTaskNum, keyNum, partitionsNum);
      }
    }
  }

  @Test
  public void testBucketPartitionIndexPartitioner03() {
    int bucketNum = 1;
    int subTaskNum = 4;
    String recordKeyField = "job_id";
    BucketPartitionIndexPartitioner partitioner = new BucketPartitionIndexPartitioner(bucketNum, recordKeyField);
    HoodieKey hoodieKey = new HoodieKey("8a850cb080595b7801806466416f0521", "dt=2022-04-26");
    System.out.println(partitioner.partition(hoodieKey, subTaskNum));
  }

  @Test
  public void bucketAndFileIdTest() {

  }

  public void testSameBucketPartitionsInSameSubTask(int bucketNum, int subTaskNum, int keyNum, int partitionsNum) {
    String recordKeyField = "_row_key";
    BucketPartitionIndexPartitioner partitioner = new BucketPartitionIndexPartitioner(bucketNum, recordKeyField);
    Map<Integer, List<HoodieKey>> partitionNo2HudiKey = new HashMap<>();
    Map<Integer, List<Pair<HoodieKey, Integer>>> bucketId2KeyAndSubTask = new LinkedHashMap<>();
    for (int i = 0; i < keyNum; i++) {
      String key = recordKeyField + ":" + i;
      for (int j = 0; j < partitionsNum; j++) {
        String partitionPath = "/data/base/" + j;
        HoodieKey hoodieKey = new HoodieKey(key, partitionPath);
        int subTaskNo = partitioner.partition(hoodieKey, subTaskNum);
        partitionNo2HudiKey.computeIfAbsent(subTaskNo, k -> new ArrayList<>()).add(hoodieKey);
        bucketId2KeyAndSubTask.computeIfAbsent(
          BucketIdentifier.getBucketId(hoodieKey, recordKeyField, bucketNum), k -> new ArrayList<>()).add(Pair.of(hoodieKey, subTaskNo));
      }
    }
//    partitionNo2HudiKey.forEach((k, v) -> v.forEach(hKey->System.out.println("SubTask:" + k + " | key:" + hKey)));
//    partitionNo2HudiKey.forEach((k, v) -> {
//      v.sort(Comparator.comparing(HoodieKey::getPartitionPath));
//      System.out.println("SubTask:" + k + "| size:" + v.size());
//      v.forEach(
//        hKey -> System.out.println("    BucketId:" + BucketIdentifier.getBucketId(hKey, recordKeyField, bucketNum)
//          + "| recordKey:" + hKey)
//      );
//    });
//    System.out.println("===============================");
    Map<Pair<Integer, String>, List<Pair<HoodieKey, Integer>>> bucketIdAndPartitionPath2KeyAndTaskNo = new LinkedHashMap<>();
    bucketId2KeyAndSubTask.forEach((bucketId, keyAndTaskNoList) -> {
      keyAndTaskNoList.sort(Comparator.comparing(e -> e.getLeft().getPartitionPath()));

//      System.out.println("BucketId:" + bucketId + "| size:" + keyAndTaskNoList.size());
      keyAndTaskNoList.forEach(keyAndTaskNo -> {
//          System.out.println("    subTask:" + keyAndTaskNo.getRight()
//            + "| recordKey:" + keyAndTaskNo.getLeft());
          bucketIdAndPartitionPath2KeyAndTaskNo.computeIfAbsent(
              Pair.of(bucketId, keyAndTaskNo.getLeft().getPartitionPath()), k -> new ArrayList<>())
            .add(Pair.of(keyAndTaskNo.getLeft(), keyAndTaskNo.getRight())
            );
        }
      );
    });
    System.out.println("===============================");
    bucketIdAndPartitionPath2KeyAndTaskNo.forEach((bucketIdAndPartitionPath, keyAndTaskNoList) -> {
      System.out.println("BucketId:" + bucketIdAndPartitionPath.getLeft() + "|partition:" + bucketIdAndPartitionPath.getRight()
        + "| size:" + keyAndTaskNoList.size());
      AtomicInteger lastSubTask = new AtomicInteger(-1);
      StringBuilder lastPartitionPath = new StringBuilder();
      keyAndTaskNoList.forEach(keyAndTaskNo -> {
        System.out.println("    subTask:" + keyAndTaskNo.getRight()
          + "| recordKey:" + keyAndTaskNo.getLeft());
        if (lastSubTask.get() < 0) {
          lastSubTask.set(keyAndTaskNo.getRight());
        } else {
          Assertions.assertEquals(lastSubTask.get(), keyAndTaskNo.getRight(),
            "subTask not equal in bucketId:" + bucketIdAndPartitionPath.getLeft()
              + "|path:" + bucketIdAndPartitionPath.getRight() + "| lastPath:" + lastPartitionPath.toString());
        }
        lastPartitionPath.delete(0, lastPartitionPath.length());
        lastPartitionPath.append(bucketIdAndPartitionPath.getRight());
      });
    });
  }

}
