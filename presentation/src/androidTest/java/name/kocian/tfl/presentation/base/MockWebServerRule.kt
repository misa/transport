package name.kocian.tfl.presentation.base

import name.kocian.tfl.datasource.di.NetworkModule
import name.kocian.tfl.presentation.util.JsonFile
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class MockWebServerRule : TestRule {

    companion object {
        lateinit var server: MockWebServer

        fun enqueue(fileName: String) {
            server.enqueue(MockResponse().setBody(JsonFile(fileName).load()))
        }
    }

    override fun apply(base: Statement, description: Description) = MockMockWebServerStatement(base)

    inner class MockMockWebServerStatement(private val statement: Statement) : Statement() {

        override fun evaluate() {

            fun initMockWebServer() {
                server = MockWebServer()
                server.start()

                NetworkModule.baseUrl = server.url("").toString()
            }

            try {
                initMockWebServer()
                statement.evaluate()
            } finally {
                server.shutdown()
            }
        }
    }
}
