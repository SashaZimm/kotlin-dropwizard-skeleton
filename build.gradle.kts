import org.gradle.jvm.tasks.Jar

repositories {
    mavenCentral()
    maven { setUrl("http://dl.bintray.com/jetbrains/spek") }
}

plugins {
    application
    kotlin("jvm") version "1.2.10"
}

application {
    mainClassName = "BackendApplication"
}

val dropwizardVersion by project

dependencies {
    compile(kotlin("stdlib", "1.2.10"))
    compile("io.dropwizard:dropwizard-core:$dropwizardVersion")

    (testCompile("org.jetbrains.spek:spek-api:1.1.5") as ModuleDependency).apply { exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib") }
    testRuntime("org.jetbrains.spek:spek-junit-platform-engine:1.1.5")
    testCompile("org.junit.platform:junit-platform-runner:1.0.2")
    testCompile("org.assertj:assertj-core:3.8.0")
    testCompile("io.dropwizard:dropwizard-testing:$dropwizardVersion")
    testCompile("io.dropwizard:dropwizard-client:$dropwizardVersion")
}

task<Wrapper>("wrapper") {
    gradleVersion = "4.4"
}

val fatJar = task<Jar>("fatJar") {
    manifest {
        attributes["Implementation-Title"] = "Kotlin Dropwizard Skeleton"
        attributes["Implementation-Version"] = project.version
        attributes["Main-Class"] = "BackendApplication"
    }
    baseName = project.name
    from(configurations.compile.map({ if (it.isDirectory) it else zipTree(it) }))
    with(tasks["jar"] as CopySpec)
}

tasks {
    "build" {
        dependsOn(fatJar)
    }
}

val run by tasks.getting(JavaExec::class) {
    args = mutableListOf("server", "backend.yaml")
}
