package ui;

import lombok.extern.log4j.Log4j;
import org.testng.annotations.Listeners;
import ui.listeners.TestListener;

@Log4j
@Listeners({TestListener.class})
public class BaseSetting {
}
