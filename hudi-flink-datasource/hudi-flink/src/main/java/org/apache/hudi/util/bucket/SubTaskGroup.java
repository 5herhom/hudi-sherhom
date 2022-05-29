package org.apache.hudi.util.bucket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sherhom
 * @date 2022/05/27 18:57
 */
public class SubTaskGroup {
    private final int groupId;
    private Map<Integer, SubTaskAssignInfo> taskIdToSubTaskInfo = new HashMap<>();
    private List<SubTaskAssignInfo> subTaskList = new ArrayList<>();

    public SubTaskGroup(int groupId) {
        this.groupId = groupId;
    }

    public int getGroupId() {
        return groupId;
    }

    public Map<Integer, SubTaskAssignInfo> getTaskIdToSubTaskInfo() {
        return taskIdToSubTaskInfo;
    }

    public void addSubTaskInfo(SubTaskAssignInfo subTaskAssignInfo) {
        taskIdToSubTaskInfo.put(subTaskAssignInfo.getTaskId(), subTaskAssignInfo);
        subTaskList.add(subTaskAssignInfo);
    }

    public SubTaskAssignInfo getSubTaskInfoById(Integer taskId) {
        return taskIdToSubTaskInfo.get(taskId);
    }

    public SubTaskAssignInfo getSubTaskInfoByPosition(Integer position) {
        return subTaskList.get(position);
    }
    public int size(){
        return subTaskList.size();
    }

    @Override
    public String toString() {
        return "SubTaskGroup{" +
                "groupId=" + groupId +
                ", subTaskList=" + subTaskList +
                '}';
    }
}
