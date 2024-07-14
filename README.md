# Apica Project

This project includes two microservices, <span class="highlight">UserService</span> and <span class="highlight">JournalService</span>, along with a Kafka broker and Zookeeper for event streaming.

## Prerequisites

- Docker
- Docker Compose

## Project Structure

```bash
Apica/
├── docker-compose.yml
├── JournalService/
│ ├── Dockerfile
│ └── ...
└── UserService/
├── Dockerfile
└── ...

```

## Getting Started

### Build and Start Services

To start using the project, clone the repository and navigate to the Apica directory:

```bash
git clone https://github.com/piyush425/Apica.git

```

```bash
cd Apica

```

Build and start all services using Docker Compose:

```bash
docker-compose up --build

```

