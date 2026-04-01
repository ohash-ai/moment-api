# 片刻 API

> 「片刻」微信小程序后端服务

## 技术栈

- Java 21
- Spring Boot 3.3.5
- Spring Data JPA
- MySQL 8
- Maven

## 快速开始

### 1. 准备数据库

```sql
CREATE DATABASE moment_api_core CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 2. 配置应用

```bash
cp src/main/resources/application.yml.example src/main/resources/application.yml
```

编辑 `application.yml`，填写数据库连接信息：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/moment_api_core?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai
    username: root
    password: your_password_here
```

### 3. 启动服务

```bash
mvn spring-boot:run
```

服务默认运行在 `http://localhost:8080`

### 4. 初始化数据

```bash
mysql -u root -p moment_api_core < src/main/resources/init-data.sql
```

---

## API 文档

所有接口均以 `/api` 为前缀。点赞相关接口需在请求头中携带用户标识。

### 请求头

| Header | 说明 | 示例 |
|--------|------|------|
| `X-User-Id` | 用户唯一标识（客户端生成的 UUID） | `u_1234567890_abc` |

---

### GET /api/featured

获取精选片刻（首页大卡）

**响应示例**

```json
{
  "id": 1,
  "date": "2026.04.01",
  "imageUrl": "https://example.com/image.jpg",
  "quote": "山川异域，风月同天",
  "source": "佚名",
  "description": "那些看似遥远的风景，其实一直都在心里。",
  "liked": false,
  "likeCount": 12
}
```

---

### GET /api/moments

获取片刻列表（按创建时间倒序）

**响应示例**

```json
[
  {
    "id": 1,
    "date": "2026.04.01",
    "imageUrl": "https://example.com/image.jpg",
    "quote": "山川异域，风月同天",
    "source": "佚名",
    "description": "那些看似遥远的风景，其实一直都在心里。",
    "liked": false,
    "likeCount": 12
  }
]
```

---

### GET /api/moments/{id}

获取单条片刻详情

**路径参数**

| 参数 | 类型 | 说明 |
|------|------|------|
| `id` | Long | 片刻 ID |

**响应示例**

```json
{
  "id": 1,
  "date": "2026.04.01",
  "imageUrl": "https://example.com/image.jpg",
  "quote": "山川异域，风月同天",
  "source": "佚名",
  "description": "那些看似遥远的风景，其实一直都在心里。",
  "liked": true,
  "likeCount": 13
}
```

---

### POST /api/moments/{id}/like

切换点赞状态（已赞则取消，未赞则点赞）

**路径参数**

| 参数 | 类型 | 说明 |
|------|------|------|
| `id` | Long | 片刻 ID |

**响应示例**

```json
{
  "liked": true,
  "likeCount": 13
}
```

---

### GET /api/config

获取应用配置

**响应示例**

```json
{
  "bottomQuote": "愿你平静如初，温柔如故"
}
```

---

## 数据库结构

```sql
-- 片刻内容
CREATE TABLE moment (
  id          BIGINT PRIMARY KEY AUTO_INCREMENT,
  date        VARCHAR(20),
  image_url   VARCHAR(500),
  quote       VARCHAR(500),
  source      VARCHAR(200),
  description TEXT,
  is_featured BOOLEAN DEFAULT FALSE,
  like_count  INT DEFAULT 0,
  created_at  DATETIME
);

-- 点赞记录
CREATE TABLE moment_like (
  id          BIGINT PRIMARY KEY AUTO_INCREMENT,
  moment_id   BIGINT NOT NULL,
  user_id     VARCHAR(100) NOT NULL,
  created_at  DATETIME,
  UNIQUE KEY uk_moment_user (moment_id, user_id)
);

-- 应用配置
CREATE TABLE app_config (
  config_key   VARCHAR(100) PRIMARY KEY,
  config_value TEXT
);
```

## 项目结构

```
src/main/java/com/moment/
├── MomentApplication.java
├── config/
│   └── CorsConfig.java
├── controller/
│   ├── MomentController.java
│   └── ConfigController.java
├── service/
│   ├── MomentService.java
│   └── ConfigService.java
├── repository/
│   ├── MomentRepository.java
│   ├── MomentLikeRepository.java
│   └── AppConfigRepository.java
├── entity/
│   ├── Moment.java
│   ├── MomentLike.java
│   └── AppConfig.java
└── dto/
    ├── MomentDTO.java
    ├── LikeResponse.java
    └── ConfigResponse.java
```

## 关联项目

- 前端小程序：[ohash-ai/moment](https://github.com/ohash-ai/moment)
