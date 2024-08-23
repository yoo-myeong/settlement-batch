package com.kotlin.settlementbatch.core.listener

import net.gpedro.integrations.slack.SlackApi
import net.gpedro.integrations.slack.SlackMessage
import org.springframework.batch.core.ChunkListener
import org.springframework.batch.core.scope.context.ChunkContext

class PurchaseConfirmedChunkListener : ChunkListener {
    private val slackWebhookUrl = "https://hooks.slack.com/services/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"

    override fun beforeChunk(context: ChunkContext) {
        sendMessage(SlackMessage("PurchaseConfirmedChunkListener::START!!"))
        super.beforeChunk(context)
    }

    override fun afterChunk(context: ChunkContext) {
        SlackMessage("PurchaseConfirmedChunkListener::END!!")
        super.afterChunk(context)
    }

    override fun afterChunkError(context: ChunkContext) {
        val slackMessage = SlackMessage(context.toString())
        slackMessage.setIcon(":bug:")
        slackMessage.setUsername("True.K")

        sendMessage(slackMessage)

        super.afterChunkError(context)
    }

    private fun sendMessage(message: SlackMessage) {
        val api = SlackApi(this.slackWebhookUrl)
        api.call(message)
    }
}
