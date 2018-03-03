import org.gradle.jvm.tasks.Jar
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper

repositories {
    mavenCentral()
    maven { setUrl("http://dl.bintray.com/jetbrains/spek") }
}

plugins {
    application
    kotlin("jvm") version "1.2.30"
}

application {
    mainClassName = "BackendApplication"
}

val dropwizardVersion by project

dependencies {
    compile(kotlin("stdlib", plugins.getPlugin(KotlinPluginWrapper::class.java).kotlinPluginVersion))
    compile("io.dropwizard:dropwizard-core:$dropwizardVersion")
    compile("io.swagger:swagger-jersey2-jaxrs:1.5.17")

    (testCompile("org.jetbrains.spek:spek-api:1.1.5") as ModuleDependency).apply { exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib") }
    testRuntime("org.jetbrains.spek:spek-junit-platform-engine:1.1.5")
    testCompile("org.junit.platform:junit-platform-runner:1.0.2")
    testCompile("org.assertj:assertj-core:3.8.0")
    testCompile("io.dropwizard:dropwizard-testing:$dropwizardVersion")
    testCompile("io.dropwizard:dropwizard-client:$dropwizardVersion")
    testCompile("io.github.robwin:assertj-swagger:0.6.0")
}

task<Wrapper>("wrapper") {
    gradleVersion = "4.5"
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
