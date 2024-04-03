### WILL (What I learned lately)

An attempt to revive TechLogs ( ❛ ͜ʖ ❛ )

#### Tech Stories & Learnings
- 

#### WIP [List/ Place holders for stories I plan to write about]

- `Migrations & Major Refactors`
  - How I converted a JS pkg to TS, & what issues I faced (node-logger)
  - Workspaces Beanstalk to k8s service optimizing for vertical scaling & cost-savings | design & migration

- `Networking, AWS, Helm, Docker, Scripting`
  - Learning to use Nginx ingresses & dynamically set route records using them
  - Jupyter/ R workspaces : User addition, Env personalization (metadata config) using bash scripting + Post-init k8s hooks
  - Creating a Python11 Docker Image for workspace & other custom image creation experiments (CloudBeaver Workspaces)

- `Lambda Layers & Observabilty`
  - Logging Layer: Async batch Export to Elastic (handling freq / on start / on exit force shipping)
  - Tracing Layer: Reusing pkg (Sending traces to central Otel Collector deployed)
  - Git Layer version Tracking for auto-updates for multiple services whenever redeployed

- `Standardizing Logs & Traces Format across the platform (pkg reuse for k8s services, lambdas, python apps)`
  - Format & Enrich logs to capture user/ session/ tenant info automatically
  - Winston wrapper for customization + leveraging Error captures & Log.Levels

- `Setting up Prometheus, Grafana & Otel Collector Tech Stack`
  - Tradeoffs of different tech stack selection & deployment approaches
  - Deploying it the first time & evolvement as per need
  - Custom API metrics capturing (prom-client)
  - Trace filtering (Collector sampling strategy)

- `Monitoring Enhancements`
  - Prometheus: cardinality & memory optimizations
  - Prometheus: Slack Alerts with Squad/ Stakeholder tagging functionality
  - Grafana: Service & use-case specific dashboards
  - Proactively tracking failed API counts (4xx, 5xx)
  - Sending alerts on anomaly levels detection (Cloudwatch Anomaly Alarms) -  for gateways, throttling, etc

- `ELK & APM Enhancements`
  - Lifecycle Index Policy Management (Automating cleaning strategies, rolling over indices, etc)
  - Enabling APM for traces

- `Evented systems & microservices`
  - Kafka monitoring, retries, client enhancements
  - Discovery/mesh
 
- `Good Practices`
  - DX feedbacks/ efficiencies/ study
  - Experimenting self-hosted AI tools for Codegen v/s subscribing Co-pilot, ChatGPT | Cost Tradeoff reports
  - Streamlining onboarding by standardizing & making bootstrap install scripts for 1 time setup idempotent
  - IDP: Backstage maintenance

- `RBAC`
  - Nested Feature Defining & Fine-tuned RBAC enhancements
