# Financial Service Platform DevOps Blueprint

This repository demonstrates a full end‑to‑end DevOps pipeline for the Financial Service Platform use case—using AWS Free‑Tier + one EC2 instance. 

- **Infrastructure as Code**: Ansible
- **Containerization**: Docker, Helm, Kubernetes (Kind)
- **CI/CD**: Jenkins (Pipeline as Code)
- **Quality & Security**: SonarQube, Fortify (AppScan)
- **Monitoring & SLAs**: Prometheus, Grafana, Nagios
- **Messaging**: Kafka, Zookeeper
- **Storage & Databases**: S3, RDS (MySQL)

---

## 🔧 Prerequisites

- AWS account (Free‑Tier enabled)
- One EC2 t2.micro with Kind installed
- Docker Hub 
- Git, Jenkins, Ansible installed locally


## 📁 Repository Structure

```plaintext
├── ansible/                  # Playbooks & roles
│   ├── playbooks/
│   │   ├── infra.yml         # VPC, EC2, security groups
│   │   └── apps.yml          # Kind node prep, Helm repos
│   └── roles/
│       ├── ec2-provision/
│       ├── Kind-install/
│       └── docker-
│           └── registry/
├── docker/                   # Dockerfiles for automation images
│   └── ansible-runner/
│       └── Dockerfile
├── jenkins/                  # Jenkins pipeline definitions
│   └── Jenkinsfile           # Master multibranch pipeline
├── grafana/                  # Dashboards & SLA configs
│   ├── dashboards/
│   │   └── service-health.json
│   └── prometheus-rules.yml  # SLA alerts
├── helm/                     # Helm charts for core services
│   └── analytics-api/
├── kafka/                    # Kafka & Zookeeper configs
├── mysql/                    # RDS setup scripts & migrations
└── README.md                 # ← You are here
```


## 🚀 Getting Started

1. **Clone**
   ```bash
   git clone https://github.com/your-org/durga-analytics-devops.git
   cd Financial_Service_INFRA
   ```

2. **Provision AWS Infrastructure** via Ansible:
   ```bash
   docker build -t ansible-runner:latest ./docker/ansible-runner
   docker run --rm -e AWS_PROFILE=infra-admin \
     -v ~/.aws:/root/.aws \
     -v $(pwd)/ansible:/ansible ansible-runner \
     ansible-playbook /ansible/playbooks/infra.yml
   ```

3. **Install Kind & Core Services**:
   ```bash
   ansible-playbook --inventory ansible/inventory.ini \
     ansible/playbooks/apps.yml
   ```

4. **Push Microservice Images**
   ```bash
   cd helm/analytics-api
   docker build -t yourhub/analytics-api:v1 .
   docker push yourhub/analytics-api:v1
   helm upgrade --install analytics-api .
   ```

5. **Wire Up Jenkins Pipeline**
   - In **jenkins/**, customize `Jenkinsfile` with your repo and credentials
   - Create a Multibranch Pipeline job in Jenkins pointing to this repo

6. **Import Grafana Dashboards & SLAs**
   ```bash
   kubectl create configmap grafana-dashboards \
     --from-file=grafana/dashboards/ \
     --namespace devops
   kubectl apply -f grafana/prometheus-rules.yml
   ```

---

## 📈 Monitoring & SLAs

- **Prometheus** scrapes Kind metrics and rules in `prometheus-rules.yml`.
- **Grafana** auto‑loads dashboards from `grafana/dashboards/`.
- **Nagios** (optional) uses `ansible/roles` to deploy checks for uptime.


## 🔐 Security & Scanning

- **SonarQube**: integrated in Jenkins pipeline (`withSonarQubeEnv('sonar')`).
- **Fortify**: invoke AppScan CLI in the `Scan` stage.


## 🤝 Contributing

Pull requests welcome. Please file an issue if you find bugs or want enhancements.

---

© 2025 Financial Service Platform DEMO

