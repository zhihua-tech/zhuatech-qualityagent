/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.qualityagent.controller;
import cn.zhuatech.qualityagent.agent.AgentRuntime;
import cn.zhuatech.qualityagent.common.ApiResponse;
import cn.zhuatech.qualityagent.dto.QualityAgentDto.*;
import cn.zhuatech.qualityagent.service.QualityAgentService;
import cn.zhuatech.qualityagent.service.RootCauseService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController @RequestMapping("/api/shopfloor") @PreAuthorize("hasAnyRole('DOMAIN_USER','ADMIN')")
public class WorkspaceController {
 private final QualityAgentService service; private final AgentRuntime runtime; private final RootCauseService domainAgent;
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public WorkspaceController(QualityAgentService service,AgentRuntime runtime,RootCauseService domainAgent){this.service=service;this.runtime=runtime;this.domainAgent=domainAgent;}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @GetMapping("/dashboard") public ApiResponse<Dashboard> dashboard(){return ApiResponse.ok(service.shopfloorDashboard());}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @PostMapping("/work-orders/{id}/reports") public ApiResponse<ReportResult> report(@PathVariable Long id,@Valid @RequestBody ReportRequest request){return ApiResponse.ok("反馈提交成功",service.report(id,request));}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @PostMapping("/agent-preview") public ApiResponse<AgentRuntime.AgentResult> preview(@RequestBody Map<String,String> body){return ApiResponse.ok(runtime.run(new AgentRuntime.AgentRequest(body.getOrDefault("objective","分析当前业务事项"),Map.of("mode","demo","approval","required"))));}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @PostMapping("/root-cause") public ApiResponse<RootCauseService.CauseResult> domainAction(@Valid @RequestBody RootCauseService.CauseRequest request){return ApiResponse.ok("质量根因辅助分析完成",domainAgent.analyze(request));}
}

