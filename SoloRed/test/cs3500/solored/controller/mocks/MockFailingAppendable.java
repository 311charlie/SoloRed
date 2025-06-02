package cs3500.solored.controller.mocks;

import java.io.IOException;

/**
 * Represents a failing appendable to test with.
 */
public class MockFailingAppendable implements Appendable {
  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("Fail");
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("Fail");
  }

  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("Fail");
  }
}