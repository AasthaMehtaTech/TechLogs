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
- Well the day started with me resolving an lambda-logging integration query for user-management service, where in we found that the culprit was the tracing layer that was also introduced along with logging (lambda-logging-arch: for which I had enhanced a node-logging package from js to ts, that standardizes log format & exports to ES, here I had converted sync logger url calls to just a console log which are picked by lambda logging layer with log-extension-api enabled & configured to send the console logs to Elastic search in async way).
- Learning from above was definitely integrating one thing at a time, when incorporating chnages which may break the systems.
- And regarding the actual failure cause: it was because otel sdk package wrapping which we had built our tracing package & AWS layer, was incompatible with lambdas following old style code with fn (event, callback).
- This was actually a known issue & documented, but missed while incorporating the change. Doc Learnings: highlight potential breaking changes at the beginning of integration docs itself, so that it saves the code & debug efforts!
- Then on, some learnings around putting structured milestone based approach when dealing with ambiguous large chunk of work during refinement calls; so that we don't fall into rabbithole of repeatedly navigating & updating original epic... Iykyk - XD
- Then some deep R&D work regarding learning of fetching/scraping of istio metrics with prometheus, conditionally dropping/keeping using relabel/regex strategies - in regards to the dropping of all istio metrics on droppping mountpoint label issue..
- Ref Links: https://meteatamel.wordpress.com/2019/01/07/application-metrics-in-istio/, https://medium.com/quiq-blog/prometheus-relabeling-tricks-6ae62c56cbda, learnt more abt default & most probable configured prom scraping jobs & Drop unnecessary metrics/ time-series (metrics with the specific labels)/ sensitive or unwanted labels/ Amend label format of the final metrics.., https://medium.com/@emirmujic/collect-cluster-and-istio-metrics-with-prometheus-operator-42d4499d621a (basic enablement)

### 2 Nov 2023:
- Spent some time explaining changes to integrate Logging, Tracing to lambdas. I just realized as humans we prefer to learn from calls & QNAs better than docs due to the opportunity for understanding relevance to the very customized context & usecase. Although, this might change for RAGs(Retrieval-augmented generation) fitted in with all context exhaustively & being able to pull out most specific info in ranked manner, complemented with ability of verbal comprehension by ChatGPT.
- TF Plan Changes while deployment issue sorted by using count param of TF resource, which we can conditionally set to 0 or n, which helped in a scenario where we had 2 stages for same AWS account, & wanted to create resource only once, yet allowing separate deployment pipelines via Git Actions for both stage. (Eg: Single SNS topic, Chatbot for Cloudwatch Alarms & Prometheus Alert_Rules for Configmap for 2 envs sharing same account)
- Observations on Anomaly Alarms of AWS: [Context] I had recently setup some global/account wide Lambda Concurrency Alarms for different envs & learnt the impact for different data loads. For eg: For one of the env, we had a sporadic load resulting in much noisy alarms due to actual data breaching predicted loads, hence will soon fine-tune it for more leniency, Width of delta for the Band of predicted upper 7 lower thresholds.
- In Pre-refinement call, Revisiting to enlist Tech Debts for: platform infra, costing, security, Observabilty Stack & it's stability,. enhancement, Fine-tuning Alerts to be more actionable, less noisy, accomodating good practices & adoption of centrally managed Docker file, Resuable git workflows, etc
- Real life event date & time planning analogous to the famous Meeting Room Optimization LLD problem - My learnings outta this experience - Go in with a well researched opinion, crowd source if want more approvals & acceptance & ppl's involvement, don't try to optimize it for all, that's not possible; decide just understanding & vocalizing the trade-offs, this can happen effectively, explained reasonably when having clear set of priorities beforehand.

### 3 Nov 2023:
- Metrics on my head | Planning to dig deeper into MountPoint labels, ideas for reducing load on Prometheus by identifying, topmost resource consuming vs evaluating it's real rrequirement. (Analyzing on the Function of various metrics' usability & corresponding resource consumption cost)

### 6 Nov 2023:
- Diving into PromQL again after a long time! Revisiting it, refreshing the memory of metric type: scalar, vector, strings [here](https://www.infracloud.io/blogs/promql-prometheus-guide/#what-is-prometheus-query-language-promql)
- Other Prom Metrics exploring Articles referred:
  - https://<prometheus-url:9090>/api/v1/status/tsdb : analysis of seriesCountByMetricName, labelValueCountByLabelName, memoryInBytesByLabelName, seriesCountByLabelValuePair : https://prometheus.io/docs/prometheus/latest/querying/api/#tsdb-stats
  - https://stackoverflow.com/questions/53205590/prometheus-show-top-metrics-filtered-by-number-of-labels
  - https://www.airplane.dev/blog/promql-cheat-sheet-with-examples : scenarios of mocking timeouts/ crashloops/ etc pod states
- Learnt to cater to wider audience, how to Simplify explaining complex concepts like app configs to serve abstract idea of having customer to change least amount of app code in codebase, such that the shared content becomes relevant to all

### 7 Nov 2023:
- Investigated into issue of API gateways failing due to signature mismatch when called through service-discovery
- Prom cardinality exploration:
  - https://source.coveo.com/2021/03/03/prometheus-memory/ : avoid high memory consumption by droppping frequent but unused labels
  - https://last9.io/blog/how-to-manage-high-cardinality-metrics-in-prometheus/
  - https://medium.com/@dotdc/prometheus-performance-and-cardinality-in-practice-74d5d9cd6230
  - https://www.robustperception.io/why-does-prometheus-use-so-much-ram/
  - https://grafana.com/blog/2022/03/21/how-relabeling-in-prometheus-works/
- Rescoped Prom Memory Reduction Spike (to first make the RED metrics available on dashboards by getting back necessary istio metrics)
- `relabel_configs vs metric_relabel_configs` (relabel_config happens before the scrape, metric_relabel_configs happens after the scrape). Prometheus needs to know what to scrape, and that's where service discovery and relabel_configs come in. Relabel configs allow you to select which targets you want scraped, and what the target labels will be. metric_relabel_configs by contrast are applied after the scrape has happened, but before the data is ingested by the storage system. So if there are some expensive metrics you want to drop, or labels coming from the scrape itself (e.g. from the /metrics page) that you want to manipulate that's where  metric_relabel_configs applies.
- _Interesting engg (observability) blogs_ : https://grafana.com/categories/engineering/
- Debugging lambda logging layer causing tenant apis to fail when data-mesh store called via service discovery: After n number of trial & errors were able to atleast reduce most errors by removing the await so that the log export to ES happens async in background, & now the error occurs only while cold start. Then to debug further, the enabled the debug layer logs via env_var (feels so good, I had added this feature while building it) & then releasing some more chnages in layer tested what exactly is causing the the customError 400/500s in lambdas. Also, due to extension retrying to register itself & lambda abruptly ending, had observed many downstream propagating issues like multiple log groups for a same timestamp.

### 8 Nov 2023:
- Continuing on lambda logs issue today, found the AWS Signature Mismatch could also be due to Time Disparities in DynamoClient calls passed via svc-discovery & even the version mismatch of aws-sdk in lambdas & layers (as both would get loaded in the same node_modules). That made me remember the struggle of placing node_modules correctly, specified way of in-nodejs folder for extensions & directly node_modules for layers, plus how I had struggled to figure out that the extensionid/name should be same as folder name & the init script name. Topic for another day tho.



