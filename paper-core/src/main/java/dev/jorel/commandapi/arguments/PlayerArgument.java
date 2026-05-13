package dev.jorel.commandapi.arguments;

/**
 * Compatibility bridge for plugins compiled against older CommandAPI versions.
 *
 * @deprecated CommandAPI 11 removed PlayerArgument. Use
 * {@link EntitySelectorArgument.OnePlayer} instead.
 */
@Deprecated(forRemoval = false)
public class PlayerArgument extends EntitySelectorArgument.OnePlayer {

    public PlayerArgument(String nodeName) {
        super(nodeName);
    }
}
