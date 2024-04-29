### Ref Links & pretext

A while back, I was working upon managing some credentials of workspace user, following is dump of all topics learnt, links used:
- wherein I had learnt about Dynamo CredStash & Node Crypto modules: https://nodejs.org/api/crypto.html, https://stackoverflow.com/questions/9427488/anybody-seen-a-crypt3-implementation-in-javascript, https://www.educative.io/answers/what-is-the-cryptocreatehmac-module
- along with linux crypt commands: https://man7.org/linux/man-pages/man3/crypt.3.html, DES algo crypt: https://man7.org/linux/man-pages/man3/des_crypt.3.html
- linux user management: useradd, adduser differences, usergrp management, chpasswd in bulk, etc, https://unix.stackexchange.com/questions/345459/whats-the-difference-between-passwd-and-chpasswd, https://www.freecodecamp.org/news/linux-how-to-add-users-and-create-users-with-useradd
- https://unix.stackexchange.com/questions/145490/how-to-make-user-passwords-shown-as-a-clear-text-in-linux,
- parsing yaml for env vars, ended up using a ConfigMap & running a bash script as postStart hook in k8s,
- sed cheatsheet: https://quickref.me/sed#google_vignette
- https://linuxize.com/post/how-to-add-and-delete-users-on-ubuntu-18-04/
- BASH: https://www.howtogeek.com/439199/15-special-characters-you-need-to-know-for-bash/, https://www.howtogeek.com/412055/37-important-linux-commands-you-should-know/, https://stackoverflow.com/questions/48316330/how-to-set-multiple-values-with-helm, https://stackoverflow.com/questions/55482354/ignore-commas-within-a-bash-variable-that-is-sent-to-a-csv-file, https://stackoverflow.com/questions/57584376/configuring-eks-express-with-https,
- k8s affinity & aliases while deploying grafana & prometheus :https://stackoverflow.com/questions/65841399/hashicorp-vault-on-k8s-getting-error-1-insufficient-memory-1-nodes-didnt-ma, 
- https://kubernetes.io/docs/concepts/scheduling-eviction/assign-pod-node/#affinity-and-anti-affinity
- https://kubernetes.io/docs/concepts/scheduling-eviction/taint-and-toleration/
- https://stackoverflow.com/questions/51946393/kubernetes-pod-warning-1-nodes-had-volume-node-affinity-conflict
- https://support.huaweicloud.com/intl/en-us/basics-cce/kubernetes_0030.html : pvc, storage class, volume
- ingress failure for webhook: https://stackoverflow.com/questions/61616203/nginx-ingress-controller-failed-calling-webhook, https://stackoverflow.com/questions/71129430/cant-deploy-ingress-object-on-eks-failed-calling-webhook-vingress-elbv2-k8s-aws, https://stackoverflow.com/questions/70424549/nginx-ingress-controller-error-admission-webhook-validate-nginx-ingress-kuber
- later discovered Nginx value for helm chart in Prometheus was different in format for different docker image versions.
<details><summary>Otel Monitoring</summary>

Integration approach
Integrate Prom-Client in Node JS microservice App and start exporting data as an another application on same POD with difference port number.

Configure POD Annotation in our default.yml file for PORT Number, Allow Scrap Data and relevant Path for exporting data.

Start looking out exported metrics on Prometheus and check their status within Targets.

Create dashboards in Grafana using Prometheus Time-Series data stated in above section with Dashboard UI samples.</details>

<details>
  <summary>First Codebuild & Helm Deploys</summary>
  
## Tasks done:
  - Setup git workflow actions, Codebuild project creation permissions, Trigger helm-install for the codebuild project & ensure pod's up & running
  - ConfigMaps for scripts files & tenant, user env values.

## Challenges & Solutions:


#### Deploy workflow action failing due to artifacts not found or not deploying the latest code :arrows_counterclockwise: :

Developed understanding that build.yml copies artifacts (pkging cf templates ymls and ts files compiled to js) later required in deploy.yml

Environment variables responsible for setting latest commit id & the git branch from where it picks that latest commit

Solution: Run env var action for your branch, run build & deploy actions again


#### CF stack creation from template failing :x: 
```
Causes: assume roles permission missing to the role for that particular code build we’re creating so that it has access on resource to perform certain actions through shared policies.

Solution: Giving code build create/delete project for certain names through your own infra centrally managed repo for GitHub agent’s permissions. 
 

NOTE: If it still fails, it might be due to CF stack in Rollback complete state (can be checked through AWS Console), in that case we need to delete the stack & run Github deploy action again.
```

#### Running helm install code build, facing permission issues & chart with that name not found in helm-repo/.tgz
```
Solution: Before triggering Code Build project of CF stack made:

Check if IAM Role of the CF stack has permissions to run code-build & deploy on eks cluster [run policy, eks-deploy policy]

Ensure that the helm chart (eg) is packaged & pushed [push into helm-charts repo by:]

helm package ./github-runners-0.0.1.tgz
helm package ../github/service-resources/infrastructure/helm-charts/workspace/
helm repo index . --merge index.yaml

Ensure that the build spec is fetching helm chart from correct branch (while helm repo add)

Bonus:light_bulb_on: : Some tips to save your time while testing of deploying workspaces multiple times

Have default (fallback) values set for every optional build params
Parameterize resources URL & such changing specs that you might require for specific deploys 
 
[NOTE: Do it cautiously & don’t forget to revert | For lower env only] Can modify buildspec from AWS console to hardcode some env variables so that you need not enter the overrides every time you trigger helm install codebuild
```

#### FAQ Commands:
Connect to k8s clusters (old & new):

```
aws eks --region eu-west-1 update-kubeconfig --name xyz --profile=xyz --role-arn xyz --alias xyz
```
##### Get or describe pod/pvc/svc & it’s status:

```
kubectl get pods -n latest-demo -w
kubectl get service -n latest-demo
kubectl describe service server-rstudio-workspace -n latest-demo
kubectl port-forward service/server-rstudio-workspace -n latest-demo 8000:80
kubectl describe pvc storage-volume-server-rstudio-workspace-0 -n latest-demo
Get pod resource capacity: (ram/memory/size)


kubectl resource-capacity -n latest-demo -p
(To run this command, need to install, setup krew & resource-capacity as : Installing · Krew  , Get CPU and Memory Usage of NODES and PODS - Kubectl | K8s | )
```
#### Helm Deployment Learnings & Temporary Fixes:
 
```
If we don’t have initial infra (eg: node grp) setup on new cluster then: use the old cluster with node selector of labels that already exist (eg: apiv2)

For resolving namespace not found/ doesn’t exist error: while running helm install add --create-namespace arg/flag to indicate resource creation on the go while deployments

If running new deployment you encounter error : has been uninstalled due to atomic being set: services already exists due to previous deployment failing & constant rollbacks:

remove -atomic flag from helm command | 

Alternatives: helm delete --purge <deployment>, 

helm uninstall <deployment>  -n <namespace>, 

helm upgrade --install --force

If pod is running & you want to access it via localhost, need to port-forwarding via service/pod:

kubectl port-forward pod/<pod-name> <local-port>:<exposed-port>    | 8000:8000

kubectl port-forward service/<service-name> <local-port>:<exposed-port> 



kubectl port-forward service/server-rstudio-workspace -n latest-demo 8000:80
{http-service, hence 80}

Kubernetes Port Forwarding - Connection refused Issue: Kubernetes Port Forwarding - Connection refused 

pvc in pending issue: (for new cluster)

[ failed to provision volume with StorageClass "gp2": rpc error: code = Internal desc = Could not create volume "pvc-200e17bf-a7c4-4e6d-aef4-fc642d87af60": could not create volume in EC2: UnauthorizedOperation: You are not authorized to perform this operation ]

Ensure that cluster role has pvc creation permissions : AmazonEKSServicePolicy, AmazonEKSClusterPolicy, etc.
```
 

#### Additional References: 
configmap.yaml , https://github.com/prometheus-community/helm-charts/blob/main/charts/prometheus/templates/server/cm.yaml 

 

#### Temporary Local Run Scripts:

```Run helm install locally by:

helm upgrade ${WORKSPACEID}-workspace . --wait --debug --install --create-namespace \
  --set workspace.id=${WORKSPACEID} \
  --set stage=${STAGE} \
  --set workspace.containerPort=${TARGETPORT:=8787} \
  --set workspace.probePort=${TARGETPORT:=8787} \
  --timeout 180s \
  --set "workspace.env[0].name=XYZ_KEY" \
  --set "workspace.env[0].value=xyz" \
 
  --namespace=${STAGE}-${TENANT} | sed -n '/USER-SUPPLIED VALUES:/q;p'
Run entrypoint scripts by:
kubectl -n <stage-tenant> exec -it <workspace-wid-0> -- bash ./config/entrypoint.sh

kubectl -n latest-demo exec -it workspace-rid2-0 -- bash ./config/entrypoint.sh
``` 
</details>
- 
