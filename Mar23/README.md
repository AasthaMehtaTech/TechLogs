## Compilation of March Months TILs(Today I Learned)

### 21 March 2023:
- Today, I'm exploring how to enable Prometheus Alert manager to `take alert rules from multiple files`
- The usecase for this was: each microservice would have deployed it's own config map & it's alert rules & the Prom Alert should detect it's change
- Eg: In my own microservice, I want to add custom rules configured on it's API metrics: like only alert if critical API fails, gives consistent 500s, disable a API alert temporarily, etc 
- (Possible by config Map Reloader sidecar & having all the service specific configmaps mounted at common place on prometheus)
- Useful Explorations & Learnings: 
  - [Consolidate into 1 alert rule file](https://stackoverflow.com/questions/72599877/prometheus-k8s-extraconfigmapmounts-fails-to-load-multiple-files-to-the-same-dir)
  - [mount the directory directly(multiple resp folders) for each configmap](https://stackoverflow.com/questions/68905578/configmap-with-multiple-destination-folders)
  - [A container using a ConfigMap as a subPath volume mount will not receive ConfigMap updates](https://stackoverflow.com/questions/48561338/how-to-correctly-mount-configmap-with-subpath-in-kubernetes-not-update-configs)
- But, Question comes how would we deploy the configmap along with service deployment, for that we already had a helm chart common to all micro-services deployed with istio, there we now need to add provision to create configmap conditionally when the passed/ overriden values.yml of that srvice contains it.
- Radically thinking of the primary problem when the architecture scales & contains 100s of micro-services, that much configmaps mounted onto prometheus pod isn't possible due to its limitations & complicated nature of updates; 
- hence we needed to think about an architecture for Prometheus Alert Management which provides out of the box capabilities to manage such large number of ever changing rules-kind-of-resources; for which prometheus operator was a good solution

### 22 March 2023:
- In Today's Tech Discussion we explored: Why we used Prometheus in first place & what were it's alternatives, 
- encompassing all its components :- server, tsdb (Time-Series DB), need of node-exporters & so on
- discussed Roadmap: SOLID arch, better ways to manage templatized project to generate & deploy new micro-svc in few seconds, also to make it such that all updates of central-template reach to the forked svc
- Concluded spike findings of not re-architecting yet to encompass Prom-Operator: decision reached by understanding tradeoffs & priorities of moment
- Well today, I'd like to log some personal feats too: like overcoming fear of some foods :) & introspection notes for my 121s:
  - In coming few months I'm targetting to branch out into different roles/responsibilities/sectors/verticals like Biz, Finance, Sales, Management along with main tech & engg work in parallel, just to get the gist of the whole ecosystem of startups & entrepreneurship
  - Good news is the switch would still be easy due to transferrable skills in these verticals like: <strong> Planning, Prioritizing, Communication, Ownership, Refinement, Driving into Action, Iterating/ Readjust considering feedback </strong>
  - & the plan to divide it all into smaller measurable chunks of work...Current plans being 
    - consistently follow: <strong> Maintain Daily Logs, Meets, Walks </strong>
    - Happy for gradually increasing clarity on thoughts & Visibility!
    - Bonus Points: Confidence boost on presentation acts
  - Track, track, track the progress, & make tasks so simple to folloe reducing the initial resistance to do it
  - Better refined, clearly scoped tasks -> Flow quickly in dev cycles. | Applying that to life, let's see how it goes...

### 23 March 2023:
- I did a grave mistake of `mounting 2 configmaps on same mountpath` & voila, encountered many difficulties while correcting the deployment. Multiple deployment edits in specs & uninstall & reinstall of helm releases finally solved it. Thankfully all of it was stateful & even after uninstalls, could retrieve the data back!

### 24 March 2023:
- I think I should probably make this a <strong> `life log/ learnings log/diary pages/ awareness captures/ prolly to even note atleast 1 achievement of the day` </strong> instead of just `Tech Logs!` So many cool stuff I wanna share..
- Btw, this is rtms(reminder to myself): How wonderful I felt living back the hustling, simple, authentic life of a Mumbaikar (Yeahh, the excitement was overloaded esp. due to visit after so long, truly, some distance makes thngs more beautiful!)
- During the train ride, I set out theme to focus on for coming weeks: <strong> `Meditation & Learning to use AI better using AI` </strong> (in context of using ChatGPT to satiate my curiosity & increase efficiency multi-folds)
- Also, how can I forget to give myself Small pat on the back for: BEING A DECENT GENERALIST! (Acknowledging consequences of not going too deep into one) but, it's really rewarding to feel that you've <strong> vast context holding capacity! </strong>
- Ooh, since we're going all non-tech today.. Good ideas I came across: 
  - Not comparing urself on each conversation we have, is indeed a super-power!
  - Doing what we enjoy :  Substack letter from Ava

### 25, 26 - Weekend: 
- Helping Packing & Ghuming! Scrolling Twitter Threads, urf need to stop to fall into `Bookmark rabbithole pattern`

### 27 March 2023: 
- Had a hard `debug day` :`) Figuring out why monitoring system doesn't capture API metrics for internal service deployment, while it does for public APIs service/primary deployment in the very same setup!!

### 28 March 2023:
- `Deep Work` done after long, setup of alert manager on Prometheus helm chart values override & developed understanding of slack-channel-msg-post-hooks (Well, sticky note To-Dos helped!)

### 29 March 2023:
- Presented on how to setup monitoring on a brand new k8s cluster & how to relate terraform with OOPs based languages..
- Eg: `resource "helm_release" "my_prometheus" {}` can be treated analogical to `datatype "provider from terraform registry" "variable_name"`
- Resolved Infra deployment issue for alert manager & rules-config..

### 30 March 2023:
- Learnt more about Auth service we internally use 
- Taught a colleague how to unlock terraform plans, explored more about how the lock state info is maintained in dynamodb & how internally `tf -force-unlock plan lock-id` works
- Discovered this simplistic yet interactive web page of AWS Security Checklist: https://awscheck.fyi/ If you wanna download complete pdf, click [here](https://github.com/AasthaMehtaTech/TechLogs/blob/main/Content/aws_security_checklist_report%20(1).pdf)
