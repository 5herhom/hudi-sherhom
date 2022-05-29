package org.apache.hudi.util.bucket;

/**
 * @author sherhom
 * @date 2022/05/27 18:53
 */
public class SubTaskAssignInfo {
  private int taskId;
  private int groupId;

  public SubTaskAssignInfo(int taskId, int groupId) {
    this.taskId = taskId;
    this.groupId = groupId;
  }

  public int getTaskId() {
    return taskId;
  }

  public int getGroupId() {
    return groupId;
  }

  @Override
  public String toString() {
    return "SubTaskAssignInfo{" +
            "taskId=" + taskId +
            ", groupId=" + groupId +
            '}';
  }
}
