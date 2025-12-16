# ✅ 1. `.gitignore`

This file is tuned specifically for:

* Java Project
* Maven
* IntelliJ IDEA
* Test artifacts
* Extent Reports
* Log files
* Screenshots
* Azure Pipelines compatibility

```gitignore
### IntelliJ IDEA ###
.idea/
*.iml
*.iws
out/
.idea_modules/
*.ipr

### Maven ###
target/
pom.xml.tag
pom.xml.releaseBackup
pom.xml.versionsBackup
pom.xml.next
release.properties

### Logs ###
logs/
*.log

### Reports ###
extent-report/
test-output/
*.html

### Screenshots ###
screenshots/
*.png

### OS Files ###
.DS_Store
Thumbs.db

### JVM ###
*.class
hs_err_pid*
replay_pid*

### Environment files ###
*.env
config/*.local.properties

### Cache ###
.cache/
```

This ensures Git contains **only source code**, not noise like build artifacts, logs, screenshots, or IDE files.

---

# ✅ 2. `README.md`

Copy–paste directly into `README.md`.

---

# Automation Framework – Selenium | Java | TestNG | Maven

This repository contains a **scalable, production-ready UI automation framework** designed for real-world enterprise automation.

The framework emphasizes:

* **Stability** (retry, waits, structured logging)
* **Maintainability** (Page Object design, reusable utilities)
* **Extensibility** (CI/CD, parallel runs, remote execution)
* **Observability** (logs, screenshots, detailed reports)

This project is being developed step-by-step as part of an automation learning and mastery roadmap.

---

## 1. Tech Stack

| Layer                   | Technology                              |
| ----------------------- | --------------------------------------- |
| Language                | Java (11 / 17)                          |
| UI Automation           | Selenium WebDriver 4.x                  |
| Test Runner             | TestNG                                  |
| Build & Dependency Mgmt | Maven                                   |
| Reporting               | Extent Reports                          |
| Logging                 | Log4j2                                  |
| Test Data               | CSV, Excel (Apache POI), JSON (Jackson) |
| CI/CD                   | Azure Pipelines                         |
| Browser Execution       | Local, Selenium Grid, BrowserStack      |

---

## 2. Framework Architecture

```text
automation-framework/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.<company>.framework/
│   │   │       ├── driver/
│   │   │       ├── factory/
│   │   │       ├── listeners/
│   │   │       ├── pages/
│   │   │       ├── reporting/
│   │   │       ├── utils/
│   │   └── resources/
│   │       ├── config.properties
│   │       ├── qa.properties
│   │       ├── stage.properties
│   │       └── log4j2.xml
│   └── test/
│       └── java/
│           └── com.<company>.tests/
│               ├── smoke/
│               └── regression/
├── test-data/
│   ├── users.csv
│   ├── products.json
│   └── bulk-orders.xlsx
├── logs/
├── extent-report/
├── screenshots/
├── pom.xml
├── testng.xml
├── azure-pipelines.yml
└── README.md
```

---

## 3. Key Features

### Core Capabilities

* Page Object Model (POM) with clear separation of actions & assertions
* Driver factory supporting Local + Remote + Headless executions
* Thread-safe execution for parallel testing
* Dynamic waits (Explicit Wait Strategy)
* Retry mechanism (2–3 attempts before marking failure)
* Screenshot capture on failure
* Structured logging using Log4j2
* Detailed HTML report using Extent Reports
* Environment-based configuration (dev, qa, stage)

---

## 4. Execution Options

### Local Execution

```bash
mvn clean test
```

### Environment-Specific Execution

```bash
mvn clean test -Denv=qa
```

### Parallel Execution

Configured via `testng.xml` using `parallel="tests"` and `thread-count`.

---

## 5. CI/CD Integration – Azure Pipelines

Pipeline performs:

1. Maven build + test
2. Publish reports, logs, screenshots
3. Optional notifications (email / webhook)
4. Optional BrowserStack / Selenium Grid execution

Pipeline file:
`azure-pipelines.yml`

---

## 6. Project Phases (Milestone-Based)

### Phase 1 — Foundation

* Project skeleton
* Maven setup
* Selenium + TestNG integration
* Basic Page Objects
* First smoke test (Login)
* Config loader

**Outcome:** Framework compiles & runs locally.

---

### Phase 2 — Framework Strengthening

* Driver Factory (local + remote)
* Explicit waits utility
* Retry mechanism
* Screenshot utility
* TestNG listeners
* Log4j2 logging

**Outcome:** Stable, retriable tests with logs & screenshots.

---

### Phase 3 — Reporting & Data Handling

* Extent Reports integration
* CSV, Excel, JSON data providers
* Data-driven tests
* Report customization

---

### Phase 4 — Real Test Coverage

* Smoke & regression suites
* Large dataset validations
* End-to-end flows

---

### Phase 5 — CI/CD (Azure)

* Azure pipeline YAML
* Artifact publishing
* Parameterized execution

---

### Phase 6 — Scaling & Remote Execution

* Selenium Grid
* BrowserStack / LambdaTest
* Cross-browser strategy

---

### Phase 7 — Advanced Enhancements (Optional)

* Dockerization
* Hybrid UI + API framework
* Test data generator
* Custom dashboards
* Git hooks

---

## 7. Goals

### Short-Term Goals

* Master automation fundamentals
* Build a production-grade framework
* Understand real test architectures

### Long-Term Goals

* Be interview-ready
* Automate full workflows
* CI/CD mastery
* Framework design confidence

---

## 8. Tasks To Do Next

1. Config loader
2. Driver manager
3. Base test class
4. Log4j2 setup
5. Extent Reports
6. First smoke test
7. Retry logic
8. First stable run

---

## 9. Contact / Support

*Add your contact details here.*

---
