#!/usr/bin/env node

import { readFileSync, writeFileSync } from "node:fs";
import { resolve } from "node:path";
import process from "node:process";
import { fileURLToPath } from "node:url";

const repoRoot = resolve(fileURLToPath(new URL("..", import.meta.url)));
const manifest = JSON.parse(readFileSync(resolve(repoRoot, "ports/local-ports.json"), "utf8"));
const checkOnly = process.argv.includes("--check");
const changedFiles = [];

const APPLICATION_ORDER = [
  "flowable-engine",
  "mysql-migrations",
  "todo-app",
  "spring-security",
  "spring-security-opaque",
  "gateway",
  "oauth2-gateway-mvc",
  "spring-security-reactive",
  "spring-security-reactive-opaque",
  "oauth2-authorization-server",
  "oauth2-authorization-server-opaque",
];

const ROOT_README_DEPENDENCIES = {
  "flowable-engine": () => `MySQL on \`${supportingPort("flowable-engine-mysql")}\``,
  "mysql-migrations": () => `MySQL on \`${supportingPort("mysql-migrations-mysql")}\``,
  "todo-app": () => `PostgreSQL on \`${supportingPort("todo-app-postgres")}\``,
  "spring-security": () => "JWT issuer from `oauth2-authorization-server`",
  "spring-security-opaque": () => "Opaque introspection against `oauth2-authorization-server-opaque`",
  gateway: () => "Redis plus OIDC issuer from `oauth2-authorization-server`",
  "oauth2-gateway-mvc": () => "No custom routes by default",
  "spring-security-reactive": () => "JWT issuer from `oauth2-authorization-server`",
  "spring-security-reactive-opaque": () => "Opaque introspection against `oauth2-authorization-server-opaque`",
  "oauth2-authorization-server": () => "Optional local backing services defined in `compose.yaml`",
  "oauth2-authorization-server-opaque": () => "Optional local backing services defined in `compose.yaml`",
};

const APP_RESOURCE_FILES = {
  "flowable-engine": "flowable-engine/src/main/resources/application.yml",
  "mysql-migrations": "mysql-migrations/src/main/resources/application.yml",
  "todo-app": "todo-app/src/main/resources/application.yml",
  "spring-security": "spring-security/src/main/resources/application.yml",
  "spring-security-opaque": "spring-security-opaque/src/main/resources/application.yml",
  gateway: "gateway/src/main/resources/application.yml",
  "oauth2-gateway-mvc": "oauth2-gateway-mvc/src/main/resources/application.yml",
  "spring-security-reactive": "spring-security-reactive/src/main/resources/application.yml",
  "spring-security-reactive-opaque": "spring-security-reactive-opaque/src/main/resources/application.yml",
  "oauth2-authorization-server": "oauth2-authorization-server/src/main/resources/application.yml",
  "oauth2-authorization-server-opaque": "oauth2-authorization-server-opaque/src/main/resources/application.yml",
};

const NEWMAN_ENVIRONMENTS = [
  {
    file: "newman/flowable-engine-local.postman_environment.json",
    values: {
      "base-uri": () => localhostUrl(applicationPort("flowable-engine")),
    },
  },
  {
    file: "newman/mysql-migrations-local.postman_environment.json",
    values: {
      "base-uri": () => localhostUrl(applicationPort("mysql-migrations")),
    },
  },
  {
    file: "newman/todo-app-local.postman_environment.json",
    values: {
      "base-uri": () => localhostUrl(applicationPort("todo-app")),
    },
  },
  {
    file: "newman/oauth2-authorization-server-local.postman_environment.json",
    values: {
      "base-uri": () => localhostUrl(applicationPort("oauth2-authorization-server")),
    },
  },
  {
    file: "newman/oauth2-authorization-server-opaque-local.postman_environment.json",
    values: {
      "base-uri": () => localhostUrl(applicationPort("oauth2-authorization-server-opaque")),
    },
  },
  {
    file: "newman/gateway-local.postman_environment.json",
    values: {
      "base-uri": () => localhostUrl(applicationPort("gateway")),
      "oauth-base-uri": () => localhostUrl(applicationPort("oauth2-authorization-server")),
    },
  },
  {
    file: "newman/spring-security-local.postman_environment.json",
    values: {
      "base-uri": () => localhostUrl(applicationPort("spring-security")),
      "oauth-base-uri": () => localhostUrl(applicationPort("oauth2-authorization-server")),
    },
  },
  {
    file: "newman/spring-security-reactive-local.postman_environment.json",
    values: {
      "base-uri": () => localhostUrl(applicationPort("spring-security-reactive")),
      "oauth-base-uri": () => localhostUrl(applicationPort("oauth2-authorization-server")),
    },
  },
  {
    file: "newman/spring-security-opaque-local.postman_environment.json",
    values: {
      "base-uri": () => localhostUrl(applicationPort("spring-security-opaque")),
      "oauth-base-uri": () => localhostUrl(applicationPort("oauth2-authorization-server-opaque")),
    },
  },
  {
    file: "newman/spring-security-reactive-opaque-local.postman_environment.json",
    values: {
      "base-uri": () => localhostUrl(applicationPort("spring-security-reactive-opaque")),
      "oauth-base-uri": () => localhostUrl(applicationPort("oauth2-authorization-server-opaque")),
    },
  },
];

syncValidateWorkflow();
syncApplicationPorts();
syncSupportingUrls();
syncComposePorts();
syncTodoUiOrigins();
syncNewmanEnvironments();
syncRootReadme();

if (checkOnly) {
  if (changedFiles.length > 0) {
    console.error("Port-managed files are out of sync:");
    for (const file of changedFiles) {
      console.error(`- ${file}`);
    }
    process.exit(1);
  }

  console.log("Port-managed files are in sync.");
} else if (changedFiles.length === 0) {
  console.log("No port-managed files needed updates.");
} else {
  console.log(`Updated ${changedFiles.length} port-managed file(s).`);
}

function syncValidateWorkflow() {
  updateTextFile(".github/workflows/validate.yml", (content) => {
    let next = content;

    next = replaceWorkflowPort(next, "module", "flowable-engine", "port", applicationPort("flowable-engine"));
    next = replaceWorkflowPort(
      next,
      "module_a",
      "oauth2-authorization-server",
      "port_a",
      applicationPort("oauth2-authorization-server"),
    );
    next = replaceWorkflowPort(next, "module_b", "gateway", "port_b", applicationPort("gateway"));
    next = replaceWorkflowPort(next, "module", "mysql-migrations", "port", applicationPort("mysql-migrations"));
    next = replaceWorkflowPort(
      next,
      "module",
      "oauth2-authorization-server",
      "port",
      applicationPort("oauth2-authorization-server"),
    );
    next = replaceWorkflowPort(
      next,
      "module",
      "oauth2-authorization-server-opaque",
      "port",
      applicationPort("oauth2-authorization-server-opaque"),
    );
    next = replaceWorkflowPort(
      next,
      "module_a",
      "oauth2-authorization-server",
      "port_a",
      applicationPort("oauth2-authorization-server"),
    );
    next = replaceWorkflowPort(next, "module_b", "spring-security", "port_b", applicationPort("spring-security"));
    next = replaceWorkflowPort(
      next,
      "module_a",
      "oauth2-authorization-server-opaque",
      "port_a",
      applicationPort("oauth2-authorization-server-opaque"),
    );
    next = replaceWorkflowPort(
      next,
      "module_b",
      "spring-security-opaque",
      "port_b",
      applicationPort("spring-security-opaque"),
    );
    next = replaceWorkflowPort(
      next,
      "module_a",
      "oauth2-authorization-server",
      "port_a",
      applicationPort("oauth2-authorization-server"),
    );
    next = replaceWorkflowPort(
      next,
      "module_b",
      "spring-security-reactive",
      "port_b",
      applicationPort("spring-security-reactive"),
    );
    next = replaceWorkflowPort(
      next,
      "module_a",
      "oauth2-authorization-server-opaque",
      "port_a",
      applicationPort("oauth2-authorization-server-opaque"),
    );
    next = replaceWorkflowPort(
      next,
      "module_b",
      "spring-security-reactive-opaque",
      "port_b",
      applicationPort("spring-security-reactive-opaque"),
    );
    next = replaceWorkflowPort(next, "module", "todo-app", "port", applicationPort("todo-app"));

    return next;
  });
}

function syncApplicationPorts() {
  for (const [module, file] of Object.entries(APP_RESOURCE_FILES)) {
    updateTextFile(file, (content) =>
      replaceOne(content, /(server:\r?\n\s+port: )\d+/, `$1${applicationPort(module)}`, `${file} server port`),
    );
  }
}

function syncSupportingUrls() {
  updateTextFile("flowable-engine/src/main/resources/application.yml", (content) =>
    replaceOne(
      content,
      /jdbc:mysql:\/\/localhost:\d+\/mydatabase/,
      `jdbc:mysql://localhost:${supportingPort("flowable-engine-mysql")}/mydatabase`,
      "flowable-engine datasource port",
    ),
  );

  updateTextFile("mysql-migrations/src/main/resources/application.yml", (content) =>
    replaceOne(
      content,
      /jdbc:mysql:\/\/localhost:\d+\/mydatabase/,
      `jdbc:mysql://localhost:${supportingPort("mysql-migrations-mysql")}/mydatabase`,
      "mysql-migrations datasource port",
    ),
  );

  updateTextFile("todo-app/src/main/resources/application.yml", (content) =>
    replaceOne(
      content,
      /jdbc:postgresql:\/\/localhost:\d+\/todo_db/,
      `jdbc:postgresql://localhost:${supportingPort("todo-app-postgres")}/todo_db`,
      "todo-app datasource port",
    ),
  );

  updateTextFile("gateway/src/main/resources/application.yml", (content) =>
    replaceOne(
      content,
      /(issuer-uri: )http:\/\/localhost:\d+/,
      `$1${localhostUrl(applicationPort("oauth2-authorization-server"))}`,
      "gateway issuer URI",
    ),
  );

  updateTextFile("oauth2-authorization-server-opaque/src/main/resources/application.yml", (content) =>
    replaceOne(
      content,
      /(introspection-uri: )http:\/\/localhost:\d+\/oauth2\/introspect/,
      `$1${localhostUrl(applicationPort("oauth2-authorization-server-opaque"), "/oauth2/introspect")}`,
      "opaque authorization server introspection URI",
    ),
  );

  updateTextFile(
    "oauth2-autoconfig/src/main/java/com/mrlonis/oauth2/autoconfig/autoconfig/OAuth2PropertiesEnvironmentPostProcessor.java",
    (content) => {
      let next = content;

      next = replaceOne(
        next,
        /("oauth2\.federate\.issuer-uri", ")http:\/\/localhost:\d+(")/,
        `$1${localhostUrl(applicationPort("oauth2-authorization-server"))}$2`,
        "oauth2 issuer URI default",
      );
      next = replaceOne(
        next,
        /("oauth2\.federate\.jwk-set-uri", ")http:\/\/localhost:\d+\/oauth2\/jwks(")/,
        `$1${localhostUrl(applicationPort("oauth2-authorization-server"), "/oauth2/jwks")}$2`,
        "oauth2 jwk-set URI default",
      );
      next = replaceOne(
        next,
        /("oauth2\.federate\.introspection-uri",\s+")http:\/\/localhost:\d+\/oauth2\/introspect(")/,
        `$1${localhostUrl(applicationPort("oauth2-authorization-server-opaque"), "/oauth2/introspect")}$2`,
        "oauth2 introspection URI default",
      );

      return next;
    },
  );
}

function syncComposePorts() {
  updateTextFile("flowable-engine/compose.yaml", (content) =>
    replaceOne(
      content,
      /"\d+:3306"/,
      `"${supportingPort("flowable-engine-mysql")}:3306"`,
      "flowable-engine compose port",
    ),
  );
  updateTextFile("mysql-migrations/compose.yaml", (content) =>
    replaceOne(
      content,
      /"\d+:3306"/,
      `"${supportingPort("mysql-migrations-mysql")}:3306"`,
      "mysql-migrations compose port",
    ),
  );
  updateTextFile("todo-app/compose.yaml", (content) =>
    replaceOne(content, /"\d+:5432"/, `"${supportingPort("todo-app-postgres")}:5432"`, "todo-app compose port"),
  );
}

function syncTodoUiOrigins() {
  const localTodoUiOrigin = localhostUrl(supportingPort("todo-app-ui"));

  updateTextFile(
    "todo-app/src/main/java/com/mrlonis/todo/todo_service/controllers/TodoItemsController.java",
    (content) => replaceOne(content, /http:\/\/localhost:\d+/, localTodoUiOrigin, "todo item controller CORS origin"),
  );
  updateTextFile(
    "todo-app/src/main/java/com/mrlonis/todo/todo_service/controllers/MetadataController.java",
    (content) => replaceOne(content, /http:\/\/localhost:\d+/, localTodoUiOrigin, "metadata controller CORS origin"),
  );
}

function syncNewmanEnvironments() {
  for (const environment of NEWMAN_ENVIRONMENTS) {
    updateJsonFile(environment.file, (document) => {
      for (const [key, valueFactory] of Object.entries(environment.values)) {
        const entry = document.values.find((candidate) => candidate.key === key);
        if (!entry) {
          throw new Error(`Could not find "${key}" in ${environment.file}`);
        }
        entry.value = valueFactory();
      }

      return document;
    });
  }
}

function syncRootReadme() {
  updateTextFile("README.md", (content) =>
    replaceBetweenMarkers(content, "<!-- PORTS_TABLE:START -->", "<!-- PORTS_TABLE:END -->", buildRootReadmeTable()),
  );
}

function buildRootReadmeTable() {
  const lines = ["| Module | Local port | Local dependency |", "| --- | --- | --- |"];

  for (const module of APPLICATION_ORDER) {
    lines.push(`| \`${module}\` | \`${applicationPort(module)}\` | ${ROOT_README_DEPENDENCIES[module]()} |`);
  }

  lines.push("");
  lines.push("Notes:");
  lines.push("");
  lines.push(
    `- \`gateway\` and \`oauth2-gateway-mvc\` both default to port \`${applicationPort("gateway")}\` when the \`local\` profile is active, so run one at a time or override \`server.port\`.`,
  );
  lines.push(
    "- The XML sample apps do not define dedicated local ports; they use the default Spring Boot port unless you override it.",
  );

  return lines.join("\n");
}

function updateTextFile(relativePath, transform) {
  const absolutePath = resolve(repoRoot, relativePath);
  const currentContent = readFileSync(absolutePath, "utf8");
  const nextContent = transform(currentContent);
  writeIfChanged(relativePath, currentContent, nextContent);
}

function updateJsonFile(relativePath, transform) {
  const absolutePath = resolve(repoRoot, relativePath);
  const currentContent = readFileSync(absolutePath, "utf8");
  const currentDocument = JSON.parse(currentContent);
  const nextDocument = transform(currentDocument);
  const nextContent = `${JSON.stringify(nextDocument, null, "\t")}\n`;
  writeIfChanged(relativePath, currentContent, nextContent);
}

function writeIfChanged(relativePath, currentContent, nextContent) {
  if (currentContent === nextContent) {
    return;
  }

  changedFiles.push(relativePath);

  if (!checkOnly) {
    writeFileSync(resolve(repoRoot, relativePath), nextContent, "utf8");
  }
}

function replaceWorkflowPort(content, moduleKey, moduleName, portKey, portValue) {
  const pattern = new RegExp(`(${moduleKey}: ${escapeRegex(moduleName)}\\r?\\n\\s+${portKey}: )\\d+`);
  return replaceOne(content, pattern, `$1${portValue}`, `${moduleKey} ${moduleName}`);
}

function replaceBetweenMarkers(content, startMarker, endMarker, replacement) {
  const pattern = new RegExp(`${escapeRegex(startMarker)}[\\s\\S]*?${escapeRegex(endMarker)}`);
  return replaceOne(content, pattern, `${startMarker}\n${replacement}\n${endMarker}`, `${startMarker} block`);
}

function replaceOne(content, pattern, replacement, description) {
  if (!pattern.test(content)) {
    throw new Error(`Could not update ${description}`);
  }

  return content.replace(pattern, replacement);
}

function applicationPort(module) {
  return getNumber(manifest.applicationPorts, module, "applicationPorts");
}

function supportingPort(name) {
  return getNumber(manifest.supportingPorts, name, "supportingPorts");
}

function getNumber(source, key, sourceName) {
  if (!Object.hasOwn(source, key)) {
    throw new Error(`Missing ${sourceName}.${key} in ports/local-ports.json`);
  }

  const value = source[key];
  if (typeof value !== "number") {
    throw new Error(`Expected ${sourceName}.${key} to be a number`);
  }

  return value;
}

function localhostUrl(port, path = "") {
  return `http://localhost:${port}${path}`;
}

function escapeRegex(value) {
  return value.replace(/[.*+?^${}()|[\]\\]/g, "\\$&");
}
