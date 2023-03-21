# 21 March 2023:
- Today, I'm exploring how to enable Prometheus Alert manager to take alert rules from multiple files
- The usecase for this was: each microservice would have deployed it's own config map & it's alert rules & the Prom Alert should detect it's change
- Eg: In my own microservice, I want to add custom rules configured on it's API metrics: like only alert if critical API fails, gives consistent 500s, disable a API alert temporarily, etc 
- (Possible by config Map Reloader sidecar & having all the service specific configmaps mounted at common place on prometheus)
- Useful Explorations & Learnings: 
  - [Consolidate into 1 alert rule file](https://stackoverflow.com/questions/72599877/prometheus-k8s-extraconfigmapmounts-fails-to-load-multiple-files-to-the-same-dir)
  - [mount the directory directly(multiple resp folders) for each configmap](https://stackoverflow.com/questions/68905578/configmap-with-multiple-destination-folders)
  - [A container using a ConfigMap as a subPath volume mount will not receive ConfigMap updates](https://stackoverflow.com/questions/48561338/how-to-correctly-mount-configmap-with-subpath-in-kubernetes-not-update-configs)
