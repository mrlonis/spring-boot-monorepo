#!/usr/bin/env node
// @ts-check

import { readFileSync, writeFileSync } from "node:fs";
import { resolve } from "node:path";
import process, { argv } from "node:process";
import { fileURLToPath } from "node:url";

/**
 * @typedef {Record<string, number>} PortMap
 */

/**
 * @typedef {object} PortManifest
 * @property {PortMap} applicationPorts
 * @property {PortMap} supportingPorts
 */

/**
 * @typedef {() => string} StringFactory
 */

/**
 * @typedef {Record<string, StringFactory>} RootReadmeDependencies
 */

/**
 * @typedef {Record<string, string>} AppResourceFiles
 */

/**
 * @typedef {object} NewmanEnvironmentDocumentValue
 * @property {string} key
 * @property {string} value
 */

/**
 * @typedef {object} NewmanEnvironmentDocument
 * @property {NewmanEnvironmentDocumentValue[]} values
 */

/**
 * @typedef {object} NewmanEnvironment
 * @property {string} file
 * @property {Record<string, StringFactory>} values
 */

/**
 * @template T
 * @param {string} filePath
 * @returns {T}
 */
function readJsonFile(filePath) {
  return JSON.parse(readFileSync(filePath, "utf8"));
}

const repoRoot = resolve(fileURLToPath(new URL("..", import.meta.url)));

/** @type {PortManifest} */
const manifest = readJsonFile(resolve(repoRoot, "ports/local-ports.json"));

const checkOnly = argv.includes("--check");

/** @type {string[]} */
const changedFiles = [];

/** @type {readonly string[]} */
const APPLICATION_ORDER = [
  "dual-datasources",
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
  "template-servlet",
  "template-reactive",
];

/** @type {RootReadmeDependencies} */
const ROOT_README_DEPENDENCIES = {
  "dual-datasources": () => "No dependencies",
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
  "template-servlet": () => "No dependencies",
  "template-reactive": () => "No dependencies",
};

/** @type {AppResourceFiles} */
const APP_RESOURCE_FILES = {
  "dual-datasources": "data/dual-datasources/src/main/resources/application.yml",
  "flowable-engine": "apps/flowable-engine/src/main/resources/application.yml",
  "mysql-migrations": "data/mysql-migrations/src/main/resources/application.yml",
  "todo-app": "apps/todo-app/src/main/resources/application.yml",
  "spring-security": "apps/spring-security/src/main/resources/application.yml",
  "spring-security-opaque": "apps/spring-security-opaque/src/main/resources/application.yml",
  gateway: "apps/gateway/src/main/resources/application.yml",
  "oauth2-gateway-mvc": "apps/oauth2-gateway-mvc/src/main/resources/application.yml",
  "spring-security-reactive": "apps/spring-security-reactive/src/main/resources/application.yml",
  "spring-security-reactive-opaque": "apps/spring-security-reactive-opaque/src/main/resources/application.yml",
  "oauth2-authorization-server": "apps/oauth2-authorization-server/src/main/resources/application.yml",
  "oauth2-authorization-server-opaque": "apps/oauth2-authorization-server-opaque/src/main/resources/application.yml",
  "template-servlet": "templates/template-servlet/src/main/resources/application.yml",
  "template-reactive": "templates/template-reactive/src/main/resources/application.yml",
};

/** @type {NewmanEnvironment[]} */
const NEWMAN_ENVIRONMENTS = [
  {
    file: "newman/dual-datasources-local.postman_environment.json",
    values: {
      "base-uri": () => localhostUrl(applicationPort("dual-datasources")),
    },
  },
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
  {
    file: "newman/template-servlet-local.postman_environment.json",
    values: {
      "base-uri": () => localhostUrl(applicationPort("template-servlet")),
    },
  },
  {
    file: "newman/template-reactive-local.postman_environment.json",
    values: {
      "base-uri": () => localhostUrl(applicationPort("template-reactive")),
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

    next = replaceWorkflowJobValues(next, "flowable-engine-integration-test", {
      module: "apps/flowable-engine",
      port: String(applicationPort("flowable-engine")),
    });
    next = replaceWorkflowJobValues(next, "gateway-integration-test", {
      module_a: "apps/oauth2-authorization-server",
      port_a: String(applicationPort("oauth2-authorization-server")),
      module_b: "apps/gateway",
      port_b: String(applicationPort("gateway")),
    });
    next = replaceWorkflowJobValues(next, "mysql-migrations-integration-test", {
      module: "data/mysql-migrations",
      port: String(applicationPort("mysql-migrations")),
    });
    next = replaceWorkflowJobValues(next, "oauth2-authorization-server-integration-test", {
      module: "apps/oauth2-authorization-server",
      port: String(applicationPort("oauth2-authorization-server")),
    });
    next = replaceWorkflowJobValues(next, "oauth2-authorization-server-opaque-integration-test", {
      module: "apps/oauth2-authorization-server-opaque",
      port: String(applicationPort("oauth2-authorization-server-opaque")),
    });
    next = replaceWorkflowJobValues(next, "spring-security-integration-test", {
      module_a: "apps/oauth2-authorization-server",
      port_a: String(applicationPort("oauth2-authorization-server")),
      module_b: "apps/spring-security",
      port_b: String(applicationPort("spring-security")),
    });
    next = replaceWorkflowJobValues(next, "spring-security-opaque-integration-test", {
      module_a: "apps/oauth2-authorization-server-opaque",
      port_a: String(applicationPort("oauth2-authorization-server-opaque")),
      module_b: "apps/spring-security-opaque",
      port_b: String(applicationPort("spring-security-opaque")),
    });
    next = replaceWorkflowJobValues(next, "spring-security-reactive-integration-test", {
      module_a: "apps/oauth2-authorization-server",
      port_a: String(applicationPort("oauth2-authorization-server")),
      module_b: "apps/spring-security-reactive",
      port_b: String(applicationPort("spring-security-reactive")),
    });
    next = replaceWorkflowJobValues(next, "spring-security-reactive-opaque-integration-test", {
      module_a: "apps/oauth2-authorization-server-opaque",
      port_a: String(applicationPort("oauth2-authorization-server-opaque")),
      module_b: "apps/spring-security-reactive-opaque",
      port_b: String(applicationPort("spring-security-reactive-opaque")),
    });
    next = replaceWorkflowJobValues(next, "template-servlet-integration-test", {
      module: "templates/template-servlet",
      port: String(applicationPort("template-servlet")),
    });
    next = replaceWorkflowJobValues(next, "template-reactive-integration-test", {
      module: "templates/template-reactive",
      port: String(applicationPort("template-reactive")),
    });
    next = replaceWorkflowJobValues(next, "todo-app-integration-test", {
      module: "apps/todo-app",
      port: String(applicationPort("todo-app")),
    });

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
  updateTextFile("apps/flowable-engine/src/main/resources/application.yml", (content) =>
    replaceOne(
      content,
      /jdbc:mysql:\/\/localhost:\d+\/mydatabase/,
      `jdbc:mysql://localhost:${supportingPort("flowable-engine-mysql")}/mydatabase`,
      "flowable-engine datasource port",
    ),
  );

  updateTextFile("data/mysql-migrations/src/main/resources/application.yml", (content) =>
    replaceOne(
      content,
      /jdbc:mysql:\/\/localhost:\d+\/mydatabase/,
      `jdbc:mysql://localhost:${supportingPort("mysql-migrations-mysql")}/mydatabase`,
      "mysql-migrations datasource port",
    ),
  );

  updateTextFile("apps/todo-app/src/main/resources/application.yml", (content) =>
    replaceOne(
      content,
      /jdbc:postgresql:\/\/localhost:\d+\/todo_db/,
      `jdbc:postgresql://localhost:${supportingPort("todo-app-postgres")}/todo_db`,
      "todo-app datasource port",
    ),
  );

  updateTextFile("apps/gateway/src/main/resources/application.yml", (content) =>
    replaceOne(
      content,
      /(issuer-uri: )http:\/\/localhost:\d+/,
      `$1${localhostUrl(applicationPort("oauth2-authorization-server"))}`,
      "gateway issuer URI",
    ),
  );

  updateTextFile("apps/oauth2-authorization-server-opaque/src/main/resources/application.yml", (content) =>
    replaceOne(
      content,
      /(introspection-uri: )http:\/\/localhost:\d+\/oauth2\/introspect/,
      `$1${localhostUrl(applicationPort("oauth2-authorization-server-opaque"), "/oauth2/introspect")}`,
      "opaque authorization server introspection URI",
    ),
  );

  updateTextFile(
    "starters/oauth2-autoconfig/src/main/java/com/mrlonis/oauth2/autoconfig/autoconfig/OAuth2PropertiesEnvironmentPostProcessor.java",
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
  updateTextFile("apps/flowable-engine/compose.yaml", (content) =>
    replaceOne(
      content,
      /"\d+:3306"/,
      `"${supportingPort("flowable-engine-mysql")}:3306"`,
      "flowable-engine compose port",
    ),
  );
  updateTextFile("data/mysql-migrations/compose.yaml", (content) =>
    replaceOne(
      content,
      /"\d+:3306"/,
      `"${supportingPort("mysql-migrations-mysql")}:3306"`,
      "mysql-migrations compose port",
    ),
  );
  updateTextFile("apps/todo-app/compose.yaml", (content) =>
    replaceOne(content, /"\d+:5432"/, `"${supportingPort("todo-app-postgres")}:5432"`, "todo-app compose port"),
  );
}

function syncTodoUiOrigins() {
  const localTodoUiOrigin = localhostUrl(supportingPort("todo-app-ui"));

  updateTextFile(
    "apps/todo-app/src/main/java/com/mrlonis/todo/todo_service/controllers/TodoItemsController.java",
    (content) => replaceOne(content, /http:\/\/localhost:\d+/, localTodoUiOrigin, "todo item controller CORS origin"),
  );
  updateTextFile(
    "apps/todo-app/src/main/java/com/mrlonis/todo/todo_service/controllers/MetadataController.java",
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
    replaceOne(
      content,
      /## Local Profiles and Ports[\s\S]*?## Module Conventions/,
      `${buildRootReadmeSection()}\n\n## Module Conventions`,
      "README Local Profiles and Ports section",
    ),
  );
}

/**
 * @returns {string}
 */
function buildRootReadmeSection() {
  return [
    "## Local Profiles and Ports",
    "",
    "Several runnable modules expose a `local` profile that turns on local ports and, in some cases, Docker Compose integration.",
    "",
    buildRootReadmeTable(),
    "",
    "## Port Management",
    "",
    "Edit `ports/local-ports.json`, then refresh the port-managed files with:",
    "",
    "```bash",
    "node scripts/sync-ports.mjs",
    "```",
    "",
    "Check for drift without writing changes:",
    "",
    "```bash",
    "node scripts/sync-ports.mjs --check",
    "```",
    "",
    "The sync refreshes Spring local config, supporting-service host ports, Newman environments, the validation workflow, and the generated port table above.",
  ].join("\n");
}

/**
 * @returns {string}
 */
function buildRootReadmeTable() {
  const header = ["Module", "Local port", "Local dependency"];
  const rows = APPLICATION_ORDER.map((module) => [
    `\`${module}\``,
    `\`${applicationPort(module)}\``,
    ROOT_README_DEPENDENCIES[module](),
  ]);
  const widths = header.map((cell, index) => Math.max(cell.length, ...rows.map((row) => row[index].length)));
  const lines = [formatMarkdownRow(header, widths, "center"), formatMarkdownDivider(widths)];

  for (const row of rows) {
    lines.push(formatMarkdownRow(row, widths, "left"));
  }

  lines.push("");
  lines.push("Notes:");
  lines.push("");
  if (applicationPort("gateway") === applicationPort("oauth2-gateway-mvc")) {
    lines.push(
      `- \`gateway\` and \`oauth2-gateway-mvc\` both default to port \`${applicationPort("gateway")}\` when the \`local\` profile is active, so run one at a time or override \`server.port\`.`,
    );
  } else {
    lines.push(
      `- \`gateway\` and \`oauth2-gateway-mvc\` now use distinct default local ports (\`${applicationPort("gateway")}\` and \`${applicationPort("oauth2-gateway-mvc")}\`), so you can run them side by side.`,
    );
  }
  lines.push(
    "- The XML sample apps do not define dedicated local ports; they use the default Spring Boot port unless you override it.",
  );

  return lines.join("\n");
}

/**
 * @param {number[]} widths
 * @returns {string}
 */
function formatMarkdownDivider(widths) {
  return `|${widths.map((width) => "-".repeat(width + 2)).join("|")}|`;
}

/**
 * @param {string[]} values
 * @param {number[]} widths
 * @param {"left" | "center"} alignment
 * @returns {string}
 */
function formatMarkdownRow(values, widths, alignment) {
  const padded = values.map((value, index) => {
    if (alignment === "center") {
      return centerPad(value, widths[index]);
    }

    return value.padEnd(widths[index], " ");
  });

  return `| ${padded.join(" | ")} |`;
}

/**
 * @param {string} value
 * @param {number} width
 * @returns {string}
 */
function centerPad(value, width) {
  if (value.length >= width) {
    return value;
  }

  const totalPadding = width - value.length;
  const leftPadding = Math.floor(totalPadding / 2);
  const rightPadding = totalPadding - leftPadding;

  return `${" ".repeat(leftPadding)}${value}${" ".repeat(rightPadding)}`;
}

/**
 * @param {string} relativePath
 * @param {(content: string) => string} transform
 * @returns {void}
 */
function updateTextFile(relativePath, transform) {
  const absolutePath = resolve(repoRoot, relativePath);
  const currentContent = readFileSync(absolutePath, "utf8");
  let nextContent;

  try {
    nextContent = normalizeLineEndingsAndTrailingNewline(transform(currentContent), currentContent);
  } catch (error) {
    handleCheckFailure(relativePath, error);
    return;
  }

  writeIfChanged(relativePath, currentContent, nextContent);
}

/**
 * @param {string} relativePath
 * @param {(document: NewmanEnvironmentDocument) => NewmanEnvironmentDocument} transform
 * @returns {void}
 */
function updateJsonFile(relativePath, transform) {
  const absolutePath = resolve(repoRoot, relativePath);
  const currentContent = readFileSync(absolutePath, "utf8");

  /** @type {NewmanEnvironmentDocument} */
  const currentDocument = JSON.parse(currentContent);

  let nextDocument;

  try {
    nextDocument = transform(currentDocument);
  } catch (error) {
    handleCheckFailure(relativePath, error);
    return;
  }

  const nextContent = normalizeLineEndings(
    `${JSON.stringify(nextDocument, null, getPreferredJsonIndent(currentContent))}\n`,
    currentContent,
  );
  writeIfChanged(relativePath, currentContent, nextContent);
}

/**
 * @param {string} relativePath
 * @param {string} currentContent
 * @param {string} nextContent
 * @returns {void}
 */
function writeIfChanged(relativePath, currentContent, nextContent) {
  if (currentContent === nextContent) {
    return;
  }

  changedFiles.push(relativePath);

  if (!checkOnly) {
    writeFileSync(resolve(repoRoot, relativePath), nextContent, "utf8");
  }
}

/**
 * @param {string} relativePath
 * @param {unknown} error
 * @returns {void}
 */
function handleCheckFailure(relativePath, error) {
  if (!checkOnly) {
    throw error;
  }

  changedFiles.push(`${relativePath} (${formatError(error)})`);
}

/**
 * @param {string} content
 * @param {string} jobName
 * @param {Record<string, string>} values
 * @returns {string}
 */
function replaceWorkflowJobValues(content, jobName, values) {
  const jobPattern = new RegExp(
    `(^  ${escapeRegex(jobName)}:\\r?\\n[\\s\\S]*?)(?=^  [a-z0-9-]+:\\r?\\n|(?![\\s\\S]))`,
    "m",
  );

  if (!jobPattern.test(content)) {
    throw new Error(`Could not find workflow job ${jobName}`);
  }

  return content.replace(jobPattern, (jobBlock) => {
    let nextJobBlock = jobBlock;

    for (const [key, value] of Object.entries(values)) {
      const keyPattern = new RegExp(`(^\\s+${escapeRegex(key)}: ).*$`, "m");

      if (!keyPattern.test(nextJobBlock)) {
        throw new Error(`Could not update ${jobName}.${key}`);
      }

      nextJobBlock = nextJobBlock.replace(keyPattern, `$1${value}`);
    }

    return nextJobBlock;
  });
}

/**
 * @param {string} content
 * @param {RegExp} pattern
 * @param {string} replacement
 * @param {string} description
 * @returns {string}
 */
function replaceOne(content, pattern, replacement, description) {
  if (!pattern.test(content)) {
    throw new Error(`Could not update ${description}`);
  }

  return content.replace(pattern, replacement);
}

/**
 * @param {string} nextContent
 * @param {string} currentContent
 * @returns {string}
 */
function normalizeLineEndingsAndTrailingNewline(nextContent, currentContent) {
  const normalized = normalizeLineEndings(nextContent, currentContent);
  const hasTrailingNewline = /\r?\n$/.test(currentContent);

  if (hasTrailingNewline) {
    return normalized;
  }

  return normalized.replace(new RegExp(`${escapeRegex(getPreferredEol(currentContent))}$`), "");
}

/**
 * @param {string} nextContent
 * @param {string} currentContent
 * @returns {string}
 */
function normalizeLineEndings(nextContent, currentContent) {
  return nextContent.replace(/\r?\n/g, getPreferredEol(currentContent));
}

/**
 * @param {string} currentContent
 * @returns {string}
 */
function getPreferredEol(currentContent) {
  return currentContent.includes("\r\n") ? "\r\n" : "\n";
}

/**
 * @param {string} currentContent
 * @returns {string | number}
 */
function getPreferredJsonIndent(currentContent) {
  const match = currentContent.match(/^( +|\t+)(?=")/m);
  return match?.[1] ?? "  ";
}

/**
 * @param {string} module
 * @returns {number}
 */
function applicationPort(module) {
  return getNumber(manifest.applicationPorts, module, "applicationPorts");
}

/**
 * @param {string} name
 * @returns {number}
 */
function supportingPort(name) {
  return getNumber(manifest.supportingPorts, name, "supportingPorts");
}

/**
 * @param {Record<string, unknown>} source
 * @param {string} key
 * @param {string} sourceName
 * @returns {number}
 */
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

/**
 * @param {number} port
 * @param {string} [path]
 * @returns {string}
 */
function localhostUrl(port, path = "") {
  return `http://localhost:${port}${path}`;
}

/**
 * @param {string} value
 * @returns {string}
 */
function escapeRegex(value) {
  return value.replace(/[.*+?^${}()|[\]\\]/g, "\\$&");
}

/**
 * @param {unknown} error
 * @returns {string}
 */
function formatError(error) {
  if (error instanceof Error) {
    return error.message;
  }

  return String(error);
}
