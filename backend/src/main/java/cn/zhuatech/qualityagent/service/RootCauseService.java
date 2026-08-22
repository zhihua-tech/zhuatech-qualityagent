/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.qualityagent.service;
import jakarta.validation.constraints.*; import org.springframework.stereotype.Service; import java.util.*;
/** 形成可验证的根因假设；不自动关闭 CAPA 或批准产品放行。 */
@Service public class RootCauseService {
 public record CauseRequest(@NotBlank String symptom,@Min(1) @Max(5) int severity,@Min(0) int recurrenceCount,@Min(0) int evidenceItems,boolean customerImpact){}
 public record CauseResult(int urgency,String route,boolean releaseBlocked,List<String> hypotheses,List<String> nextEvidence){}
 public CauseResult analyze(CauseRequest r){int urgency=Math.min(100,r.severity()*14+r.recurrenceCount()*7+(r.customerImpact()?25:0)+(r.evidenceItems()<3?10:0));boolean block=r.severity()>=4||r.customerImpact();return new CauseResult(urgency,urgency>=70?"FORMAL_8D":"FAST_CAPA",block,List.of("设备参数或工装状态偏移","材料批次或来料波动","作业方法与防错失效"),List.of("补充对照样本","核对设备维护记录","执行原因复现实验"));}}

