package vn.id.tozydev.phatom.paper

import dev.jorel.commandapi.CommandAPIPaperConfig
import vn.id.tozydev.phantom.paper.plugin.PhantomPaperPlugin

class PhantomCorePaperPlugin : PhantomPaperPlugin() {
    override val configureCommandApi: (CommandAPIPaperConfig.() -> Unit)? = {}

    override val isInvUIEnabled: Boolean = true
}
