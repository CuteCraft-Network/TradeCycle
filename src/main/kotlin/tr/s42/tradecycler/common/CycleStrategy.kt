package tr.s42.tradecycler.common

import org.bukkit.event.Listener
import tr.s42.tradecycler.strategy.KeyboardStrategy
import tr.s42.tradecycler.strategy.ShiftInteractStrategy

enum class CycleStrategy(val listener: Listener) {

    KEYBOARD(KeyboardStrategy()), SHIFT_INTERACT(ShiftInteractStrategy());

}