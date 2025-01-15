Just trying to jot down a few piece of work I did at my time at Peak & summary of what I learnt by them:

**2022**
- Well starting with the first PR I created here: it was to integrate kafka sdk and publish events for analytics on every workspace (Peak specific context) CRUD activity: 
where I learned much about serverless infra, getting configs from AWS secrets, running AWS pipeline, also wrote my first complex lambda handlers & integrated it within the step function flow.
- Other misc tasks like: modify bash scripts to add users in ec2, AWS RUN cmd, container login & debugging, installing r_packages & fix docker builds for image layers, supporting multiple user additon for workspaces
- The major migration & learning SAGA: migrating Beanstalk workspaces to EKS workspaces: design, learning, eks, helm, docker, terraform, the bkp, 
NSG retrofit (network security grp update ensuring no ec2 instance gets rotated or else we woukld lose all the custom configs), backfill tasks, fix reboot & stuck in building issues
- Landing pages & silent notification integration use web mqtt concept for various micro frontends for status updates of long running tasks instead of continuous polling
- Apt instance type calculations, understanding ecosystem chnages like Quota Management System (soft, hard limits), Image Usage, Tenant credentials, etc
- Add data lake user policy, service discovery, feature flags, frontend ais-components library, dash application kit : converting react components into python components, 
- One-timer backup scripts, Param Key Store, etc uploads & mgt for creds across kafka topics msg enriching for helm install values, CRD, creating & publishing Helm charts
- RBAC based actions enablement for finer controls & display
- DWH, GraphQL APIs, auto-shutdown cron sched & random 5 mins time jugaad for cost-saving & distr. spiky sporadic workload, 
- HPA, karpentar, Sonar Cloud, leveraging Git Actions, writing Code pipelines, creating new codebuilds,
- what goes behind Launching workspace -> playing with R packages, jupyter Notebook Image,
- Orchestrator code, infra policies, security hackathons,
- Parametrize branch name for helm deploy thru git actions, Config Map & it's mounting & update explorations, PVC,
- Istio configs, r53 routing, password encryptions, redshift iam role mapping

Developer Experience & Observability chapters here are definitely a story for some other day.
- though in short included: Open Telemetry, Prometheus, promQL, Grafana, Metrics from AWS cloudwatch by yace exporter, Traces by Otel Collector (filtering traces) & sending over to Elastic APM backend, as well as experimented with jaegar & other tools here.
- Standardizing Logging, Monitoring & instrumenting services in least code changes way, involved building common packages that could work for node, aws, python arch & even AWS lambda layers with Telemetry extensions
P.S. Only includes what is allowed to share publicly. 
