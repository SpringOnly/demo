package com.task.sky

import org.gradle.api.Plugin
import org.gradle.api.Project

class MyPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        println('buildSrc中MyPlugin执行了')

        project.task('myTask') {
            doLast {
                println('buildSrc中MyPlugin中的task执行了')
            }
        }
    }
}