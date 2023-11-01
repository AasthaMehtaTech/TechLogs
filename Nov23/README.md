## Compilation of Nov Months TILs (Today I Learned)

### Last Quarter Recap:
This is a quick context of what happened over a last few months, mentioning recent ones in details, older just listed... Starting with the major things I'm much proud of:
- Working around `Observability`, esp, Instrumenting K8s services & Lambdas through `Logs & Traces`, using `OpenTelemetry`, other CNCF projects; With an aim of Standardizing instrumentation for all the microservices across platforms with least amount of code changes for it's adoption

This involved with me starting to learn what is instrumentation & significance of `MELT (Metrics, Events, Logs, Traces)` for a service, diving into popular available tools & practices for observability stack, how are traces captured on ground level & industry standards & specs, Opentelemetry spec, **Context propagation** mechanism - B3 headers, Zone JS, etc
- For `Logging` Needs: **fluentbit/fluent d/logstash** to filter & send logs to backends like **ElasticSearch** along with `indexing` strategies & life-cycle management
- **winston** to enable log levels, transformation; **ts-logs** & other libraries performance comparison helping in **masking** logs' sensitive data & auto-generating the error stack trace/source in node-js type of services
- For `Tracing`: learning what calls could be instrumented, how to write custom hooks, enable/disable/sample traces: Generate trace via **auto-instrumentation** packages, **Otel sdk**, Collecting & Sampling via **OTel Collector**, Tracing Backends like **Elastic APM**, **Jaegar**, etc
- For `Metrics`: **kube-state-metrics** auto-gen for k8s based services, other AWS component with enablement of respective **CloudWatch metrics** along with it's periodic aggregation strategies (eg: for lambdas, API gateways, EC2, RDS, etc), `Prometheus` for storing these TSDB metrics, `Grafana` for visualing via dashboarding, `alert-manager` to send slack alerts on anomalies & std metrics rules set

### 1 Nov 2023:
- After all attempts of tracking progress in GitBook, G-Sheets, Notion, etc. restarting here to capture my Tech daily Learnings & Doings.
