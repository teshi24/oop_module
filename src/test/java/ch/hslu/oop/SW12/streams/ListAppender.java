package ch.hslu.oop.SW12.streams;

import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.Property;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListAppender extends AbstractAppender {
  private final List<LogEvent> log = new ArrayList<>();

  public ListAppender(final String name) {
    this(name, null, null, false, null);
  }

  private ListAppender(final String name, final Filter filter, final Layout<? extends Serializable> layout,
                       final boolean ignoreExceptions, final Property[] properties) {
    super(name, filter, layout, ignoreExceptions, properties);
  }

  public List<LogEvent> getLog() {
    return new ArrayList<>(log);
  }

  public void clear() {
    log.clear();
  }

  @Override
  public void append(final LogEvent logEvent) {
    log.add(logEvent);
  }
}