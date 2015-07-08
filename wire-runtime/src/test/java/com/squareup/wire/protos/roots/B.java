// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: ../wire-runtime/src/test/proto/roots.proto
package com.squareup.wire.protos.roots;

import com.squareup.wire.Message;
import com.squareup.wire.ProtoField;
import java.lang.Object;
import java.lang.Override;

public final class B extends Message {
  private static final long serialVersionUID = 0L;

  @ProtoField(
      tag = 1,
      label = Message.Label.REQUIRED
  )
  public final C c;

  public B(C c) {
    this.c = c;
  }

  private B(Builder builder) {
    this(builder.c);
    setBuilder(builder);
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) return true;
    if (!(other instanceof B)) return false;
    return equals(c, ((B) other).c);
  }

  @Override
  public int hashCode() {
    int result = hashCode;
    return result != 0 ? result : (hashCode = c != null ? c.hashCode() : 0);
  }

  @Override
  public int size() {
    int size = this.size;
    if (size == -1) {
      size = unknownFieldsSize()
          + sizeOfMessage(1, c);
    }
    this.size = size;
    return size;
  }

  public static final class Builder extends com.squareup.wire.Message.Builder<B> {
    public C c;

    public Builder() {
    }

    public Builder(B message) {
      super(message);
      if (message == null) return;
      this.c = message.c;
    }

    public Builder c(C c) {
      this.c = c;
      return this;
    }

    @Override
    public B build() {
      if (c == null) {
        throw missingRequiredFields(c, "c");
      }
      return new B(this);
    }
  }
}
