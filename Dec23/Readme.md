## December Diaries (Tech: Work, wanderings & more)

### Week 1 (1-7 December) 2023:

- More digging into Memory Profiles for Prometheus, optimizing by label drop & then today we found this: https://thenewstack.io/30-pull-requests-later-prometheus-memory-use-is-cut-in-half/,
incredible work by Bryan Boreham.
- Kafka dashboard & understanding metrics for it
- Doc for How to use kibana for app logs for DS/ App devs
- Discussions on ILM Policies, Indexing Strategies, Rollovers, etc; App config v1 demo;
-  Setting up Py Projects for local debug; Learning more abt pydantic
-  Parvati Alerts Finetuning : managing sporadic loads by less sensitive, large anomaly band ranges for lambda CW alarms

### Week 2 (8-14 December) 2023:
-  Wrote Daily progress & meta-learnings for Duggup
-  Customizing config-mngr pkg : for Class files at multiple locations;
-  Resolving class/module imports: https://stackoverflow.com/questions/4142151/how-to-import-the-class-within-the-same-directory-or-sub-directory,
https://stackoverflow.com/questions/11536764/how-to-fix-attempted-relative-import-in-non-package-even-with-init-py
- End state proposal: ppl give opinions | PR FAQ, even before implementation |  Working style towards max adoption

```
Alerts criticality Basis:
Steady use, but reliable | Post operations
more Frequent calls | retry possible
Micro/macro Alerts identification
Entry point special case | User Login: 15 mins, Unique user failures
Platform infra level | load | fractional x% of loads failing
Dep on users affected, customer impact | 
if 2 tenants affected, something is really wrong, good indicator of sys
Durational anomaly
What is even a problem & everyone will agree | who will act on it
```
