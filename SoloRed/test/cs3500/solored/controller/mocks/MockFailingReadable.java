package cs3500.solored.controller.mocks;

import java.io.IOException;
import java.nio.CharBuffer;

/**
 * Represents a failing readable to test with.
 */
public class MockFailingReadable implements Readable {

  @Override
  public int read(CharBuffer cb) throws IOException {
    throw new IOException("Mock threw IOException");
  }
}