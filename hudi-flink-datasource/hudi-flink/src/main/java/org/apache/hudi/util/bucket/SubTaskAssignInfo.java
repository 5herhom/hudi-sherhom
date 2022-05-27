package org.apache.hudi.util.bucket;

/**
 * @author sherhom
 * @date 2022/05/27 18:53
 */
public class SubTaskAssignInfo {
  private int taskId;
  private int groupId;
  private int bucketId;
  private int bucketNum;
  private int taskNum;

  public int getTaskId() {
    return taskId;
  }

  public void setTaskId(int taskId) {
    this.taskId = taskId;
  }

  public int getGroupId() {
    return groupId;
  }

  public void setGroupId(int groupId) {
    this.groupId = groupId;
  }

  public int getBucketId() {
    return bucketId;
  }

  public void setBucketId(int bucketId) {
    this.bucketId = bucketId;
  }

  public int getBucketNum() {
    return bucketNum;
  }

  public void setBucketNum(int bucketNum) {
    this.bucketNum = bucketNum;
  }

  public int getTaskNum() {
    return taskNum;
  }

  public void setTaskNum(int taskNum) {
    this.taskNum = taskNum;
  }
}
