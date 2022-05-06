package org.apache.hudi.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hudi.common.model.HoodieAvroPayload;
import org.apache.hudi.common.model.HoodieTableType;
import org.apache.hudi.common.table.HoodieTableConfig;
import org.apache.hudi.common.table.HoodieTableMetaClient;
import org.apache.hudi.common.testutils.HoodieTestUtils;
import org.apache.hudi.hadoop.realtime.HoodieParquetRealtimeInputFormat;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author sherhom
 * @date 2022/03/24 19:01
 */
public class TestHoodieParquetInputFormat01 {
    String basePath = "E:\\data\\hudi";
    JobConf jobConf = new JobConf();
    ;

    @Test
    public void readFileAfterDelete() throws IOException {
        HoodieParquetInputFormat inputFormat = new HoodieParquetInputFormat();
        HoodieTestUtils.init(jobConf, basePath, HoodieTableType.COPY_ON_WRITE);
        String table = "table01";
        String tablePath = basePath + "\\" + table;
        List<String> partitionPathList = Stream.of(
                "2020/01/01",
                "2020/01/02",
                "2020/01/03"
        ).collect(Collectors.toList());
        inputFormat.setConf(jobConf);

        inputFormat.setInputPaths(jobConf, partitionPathList.stream().map(e -> Paths.get(tablePath, e).toString()).collect(Collectors.joining(",")));
        try {
            FileStatus[] fileStatuses = inputFormat.listStatus(jobConf);
            System.out.println(Arrays.stream(fileStatuses).map(e -> e.getPath().toString()).collect(Collectors.joining("\n")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readMOR() throws IOException {
        HoodieParquetInputFormat inputFormat = new HoodieParquetRealtimeInputFormat();
        HoodieTestUtils.init(jobConf, basePath, HoodieTableType.MERGE_ON_READ);
        String table = "flink_cow_test";
        String tablePath = basePath + "\\" + table;
        List<String> partitionPathList = Stream.of(
                "par1",
                "par2",
                "par3",
                "par4"
        ).collect(Collectors.toList());
        inputFormat.setConf(jobConf);

        inputFormat.setInputPaths(jobConf, partitionPathList.stream().map(e -> Paths.get(tablePath, e).toString()).collect(Collectors.joining(",")));
        try {
            FileStatus[] fileStatuses = inputFormat.listStatus(jobConf);
            System.out.println(Arrays.stream(fileStatuses).map(e -> e.getPath().toString()).collect(Collectors.joining("\n")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readMORHdfs() throws IOException {
        System.setProperty("HADOOP_USER_NAME", "hadoop");
        String table = "flink_hudi_test_mor";
        String basePath="hdfs://sjz-t01:8020/user/hive/warehouse/fx_temp.db/flink_hudi_test_mor";
        HoodieParquetInputFormat inputFormat = new HoodieParquetRealtimeInputFormat();
//        jobConf.set(HoodieTableConfig.NAME.key(),table);
        jobConf.set("hoodie.flink_hudi_test_mor.consume.mode","INCREMENTAL");
        jobConf.set("hoodie.flink_hudi_test_mor.consume.max.commits","-1");
        jobConf.set("hoodie.flink_hudi_test_mor.consume.start.timestamp","20220506000033702");
//        initRealTeam(table, jobConf, basePath, HoodieTableType.MERGE_ON_READ);
//        String tablePath = basePath + "\\" + table;
        List<String> partitionPathList = Stream.of(
                "dt=2022-04-06"
        ).collect(Collectors.toList());
        inputFormat.setConf(jobConf);
        inputFormat.setInputPaths(jobConf, partitionPathList.stream().map(e -> basePath+"/"+e).collect(Collectors.joining(",")));
        try {
            FileStatus[] fileStatuses = inputFormat.listStatus(jobConf);
            System.out.println(Arrays.stream(fileStatuses).map(e -> e.getPath().toString()).collect(Collectors.joining("\n")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HoodieTableMetaClient initRealTeam(String tableName, Configuration hadoopConf, String basePath, HoodieTableType tableType) throws IOException {
        return initRealTeam(tableName, hadoopConf, basePath, tableType,new Properties());
    }

    public static HoodieTableMetaClient initRealTeam(String tableName, Configuration hadoopConf, String basePath, HoodieTableType tableType,
                                                     Properties properties)
            throws IOException {
        properties = HoodieTableMetaClient.withPropertyBuilder()
                .setTableName(tableName)
                .setTableType(tableType)
                .setPayloadClass(HoodieAvroPayload.class)
                .fromProperties(properties)
                .build();
        return HoodieTableMetaClient.initTableAndGetMetaClient(hadoopConf, basePath, properties);
    }
}
