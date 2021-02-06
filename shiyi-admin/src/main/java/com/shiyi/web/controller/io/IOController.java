package com.shiyi.web.controller.io;

import com.shiyi.common.core.domain.model.Result;
import com.shiyi.system.domain.SysMember;
import com.shiyi.system.service.SysMemberService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:superJar
 * @date:2021/1/7
 * @time:16:43
 * @details:
 */
@RestController
@RequestMapping("/io")
public class IOController {

    @Resource
    private SysMemberService sysMemberService;

    /**
     * @description:导入
     * @author: SuperJar
     * @date: 2021/1/7 16:45
     * @return: com.shiyi.common.core.domain.model.Result
     */
    @PostMapping("/import")
    public Result fileImport(@RequestBody MultipartFile file) throws IOException {
        List<SysMember> members = new ArrayList<>();
        XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream());
        // 获取指定sheet
        XSSFSheet memberSheet = wb.getSheetAt(3);
        boolean flag = true;
        for (int i = 2; flag; i++) {
            SysMember member = new SysMember();
            XSSFRow row = memberSheet.getRow(i);
            for (int j = 0; j < 6; j++) {
                XSSFCell cell = row.getCell(j);
                String rawValue = cell.getRawValue();

                switch (j) {
                    case 0:
                        member.setCardNum(rawValue);
                        break;
                    case 1:
                        member.setName(rawValue);
                        break;
                    case 3:
                        member.setSumOfTopUp(new BigDecimal(rawValue));
                        break;
                    case 4:
                        member.setSumOfConsumption(Double.valueOf(rawValue));
                        break;
                    case 5:
                        member.setBalance(new BigDecimal(rawValue));
                        break;
                }
            }
            members.add(member);
            String cardNum = member.getCardNum();
            if(StringUtils.isBlank(cardNum)){
                flag = false;

            }
        }
        members.remove(members.size() - 1);

        sysMemberService.batchAdd(members);
        return Result.ok();
    }
}
