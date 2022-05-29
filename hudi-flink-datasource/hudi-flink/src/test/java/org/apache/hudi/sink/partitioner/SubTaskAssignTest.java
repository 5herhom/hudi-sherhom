package org.apache.hudi.sink.partitioner;

import org.apache.hudi.util.bucket.SubTaskAssign;
import org.junit.jupiter.api.Test;

/**
 * @Author Sherhom
 * @Date 2022/5/29 17:45
 */
public class SubTaskAssignTest {
    @Test
    public void subTaskAssignTest(){
        int bucketMax = 10;
        int taskMax = 10;
        for(int bucketTotal=1;bucketTotal<=bucketMax;bucketTotal++){
            for(int taskTotal=bucketTotal;taskTotal<=taskMax;taskTotal++){
                System.out.println("BucketTotal:"+bucketTotal+";taskTotal:"+taskTotal);
                SubTaskAssign subTaskAssign=new SubTaskAssign(bucketTotal,taskTotal);
                System.out.println(subTaskAssign);
            }
        }
    }
}
