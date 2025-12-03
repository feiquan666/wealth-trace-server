package com.feiquan.tools.util.temp;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.feiquan.tools.util.JsonUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class UpdateSqlHelper {

    static String haveUser = "UPDATE union_member.t_member_switch_log SET created_at = CONCAT('startAfterDateEnd', ' ', TIME(created_at)), updated_at = CONCAT('startAfterDateEnd', ' ', TIME(updated_at)), created_by = 'startAfterUserEnd', updated_by = 'startAfterUserEnd' WHERE member_no = 'startMemberNoEnd' AND TRIM(comment) = 'startCommentEnd' AND DATE_FORMAT(created_at, '%Y-%m-%d %H:%i') = 'startSystemDateEnd';";
    static String noUser = "UPDATE union_member.t_member_switch_log SET created_at = CONCAT('startAfterDateEnd', ' ', TIME(created_at)), updated_at = CONCAT('startAfterDateEnd', ' ', TIME(updated_at)) WHERE member_no = 'startMemberNoEnd' AND TRIM(comment) = 'startCommentEnd' AND DATE_FORMAT(created_at, '%Y-%m-%d %H:%i') = 'startSystemDateEnd';";


    public static void main(String[] args) throws IOException {
        genUpdateSql();
    }

    private static void genUpdateSql() throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fileName = "/Users/tyler/Downloads/aaa_data.xlsx";
        // 读取 Excel
        List<MyExcelData> list = EasyExcel.read(fileName)
                .head(MyExcelData.class)
                .sheet()
                .doReadSync();
        List<String> sqlList = Lists.newArrayList();
        System.out.println(JsonUtil.toJson(list));
        for (MyExcelData data : list) {
            if (Objects.isNull(data)) {
                continue;
            }
            String afterUser = data.getAfterUser();
            String afterDate = data.getAfterDate().trim();
            String memberNo = data.getMemberNo().trim();
            String systemComment = data.getSystemComment().trim();
            String systemDate = data.getSystemDate().trim().replace("/", "-");
            boolean zeroTime = systemDate.endsWith("00");
            if (!zeroTime) {
                continue;
            }
            // 解析为 LocalDateTime
            LocalDateTime bjTime = LocalDateTime.parse(systemDate, formatter);
            // 指定东8区
            ZonedDateTime bjZoned = bjTime.atZone(ZoneId.of("Asia/Shanghai"));
            // 转换为 UTC
            ZonedDateTime utcTime = bjZoned.withZoneSameInstant(ZoneOffset.UTC);

            // 转成字符串，用于 SQL
            String utcTimeStr = utcTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            if (StringUtils.isNotBlank(afterUser)) {
                afterUser = afterUser.trim();
                // 会替换 user
                sqlList.add(haveUser.replace("startAfterDateEnd", afterDate)
                        .replace("startAfterUserEnd", afterUser)
                        .replace("startMemberNoEnd", memberNo)
                        .replace("startCommentEnd", systemComment)
                        .replace("startSystemDateEnd", utcTimeStr));
            } else {
                // 不会替换 user
                sqlList.add(noUser.replace("startAfterDateEnd", afterDate)
                        .replace("startMemberNoEnd", memberNo)
                        .replace("startCommentEnd", systemComment)
                        .replace("startSystemDateEnd", utcTimeStr));
            }
        }
        log.info("应生成 {} 条 SQL，实际生成 {} 条 SQL", list.size(), sqlList.size());
        String filePath = "/Users/tyler/MyProjects/feiquan/wealth-trace-server/src/main/java/com/feiquan/tools/util/temp/final_tyler_dml.sql";
        writeLinesToFile(sqlList, filePath);
    }

    public static void writeLinesToFile(List<String> lines, String filePath) throws IOException {
        Path path = Path.of(filePath);
        // 使用 try-with-resources 自动关闭
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine(); // 换行
            }
            System.out.println("写入完成：" + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Data
    @EqualsAndHashCode
    public static class MyExcelData {
        @ExcelProperty("会员号")
        private String memberNo;
        @ExcelProperty("会员名称")
        private String memberName;
        @ExcelProperty("系统date")
        private String systemDate;
        @ExcelProperty("系统comment")
        private String systemComment;
        @ExcelProperty("调整后时间")
        private String afterDate;
        @ExcelProperty("调整后操作人")
        private String afterUser;
    }


    private static void genUserIdSql() {

        List<String> a = Lists.newArrayList("fsd");
        Set<String> set = Sets.newHashSet();
        for (String s : a) {
            set.add(s.trim());
        }
        String inSql = set.stream()
                .map(s -> "'" + s + "'")
                .collect(Collectors.joining(", "));
        String sql = "select id, user_id, user_name " +
                "from union_oauth2.t_user_inner " +
                "where email in (" + inSql + ") and del_flag = 'N';";
        System.out.println(sql);
    }


}
