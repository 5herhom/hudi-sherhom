package org.apache.hudi.util.bucket;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sherhom
 * @date 2022/05/27 18:53
 */
public class SubTaskAssign {
    private Map<Integer, SubTaskGroup> groupIdToSubTaskGroup = new HashMap<>();
    private Map<Integer, SubTaskAssignInfo> taskIdToSubTaskInfo = new HashMap<>();

    public SubTaskAssign(int groupNum, int totalTaskNum) {
        if (groupNum > totalTaskNum) {
            throw new IllegalArgumentException("GroupNum is larger than task.GroupNum:"+groupNum+";totalTaskNum:"+totalTaskNum);
        }
        int groupBaseSize = totalTaskNum / groupNum;
        //    The head of {totalTaskNum % groupNum} task groups' size should be added by 1.
        int numOfGroupThatShouldBeCompensated = totalTaskNum % groupNum;
        int curTaskId = 0;
        int i = 0;
        while (i < groupNum) {
            int groupSize = i < numOfGroupThatShouldBeCompensated ?
                    groupBaseSize + 1 : groupBaseSize;
            SubTaskGroup subTaskGroup = new SubTaskGroup(i);
            for (int j = 0; j < groupSize; j++) {
                SubTaskAssignInfo subTaskAssignInfo = new SubTaskAssignInfo(curTaskId++, i);
                subTaskGroup.addSubTaskInfo(subTaskAssignInfo);
                taskIdToSubTaskInfo.put(subTaskAssignInfo.getTaskId(), subTaskAssignInfo);
            }
            addGroup(subTaskGroup);
            i++;
        }
    }

    private void addGroup(SubTaskGroup group) {
        groupIdToSubTaskGroup.put(group.getGroupId(), group);
    }

    public SubTaskGroup getGroupById(Integer groupId) {
        return groupIdToSubTaskGroup.get(groupId);
    }

    public SubTaskAssignInfo assignTask(int groupSeed, int taskSeed) {
        int groupId = groupSeed % groupIdToSubTaskGroup.size();
        SubTaskGroup taskGroup = groupIdToSubTaskGroup.get(groupId);
        int taskPos = taskSeed % taskGroup.size();
        return taskGroup.getSubTaskInfoByPosition(taskPos);
    }

    public SubTaskAssignInfo getTaskById(Integer taskId) {
        return taskIdToSubTaskInfo.get(taskId);
    }

    @Override
    public String toString() {
        return "SubTaskAssign{" +
                "groupIdToSubTaskGroup=" + groupIdToSubTaskGroup +
                '}';
    }
}
