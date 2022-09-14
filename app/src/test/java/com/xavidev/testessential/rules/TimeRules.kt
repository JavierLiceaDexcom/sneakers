package com.xavidev.testessential.rules

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

annotation class LogTimes
class TimeRules : TestRule {
    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                val annotated = description.annotations?.filterIsInstance<LogTimes>()?.isNotEmpty()
                val start = System.currentTimeMillis()
                try {
                    println("Rule starts...")
                    base.evaluate()
                    println("Rule ends...")
                } finally {
                    val final = System.currentTimeMillis()
                    annotated?.let {
                        if (it) {
                            println("The test ${description.methodName} has taken " +
                                        "${final.minus(start)} ms to finish")
                        }
                    }
                }
            }
        }
    }
}