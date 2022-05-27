package org.apache.hudi.util.bucket;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sherhom
 * @date 2022/05/27 18:57
 */
public class SubTaskGroup {
  private int groupId;
  private Map<Integer, SubTaskAssignInfo> taskNoToSubTaskInfo = new HashMap<>();

  public int getGroupId() {
    return groupId;
  }

  public void setGroupId(int groupId) {
    this.groupId = groupId;
  }

  public Map<Integer, SubTaskAssignInfo> getTaskNoToSubTaskInfo() {
    return taskNoToSubTaskInfo;
  }

  public void addSubTaskInfo(SubTaskAssignInfo subTaskAssignInfo) {
    taskNoToSubTaskInfo.put(subTaskAssignInfo.getTaskId(), subTaskAssignInfo);
  }
  public SubTaskAssignInfo getSubTaskInfo(Integer taskId){
    return taskNoToSubTaskInfo.get(taskId);
  }
  public void setTaskNoToSubTaskInfo(Map<Integer, SubTaskAssignInfo> taskNoToSubTaskInfo) {
    this.taskNoToSubTaskInfo = taskNoToSubTaskInfo;
  }
}
