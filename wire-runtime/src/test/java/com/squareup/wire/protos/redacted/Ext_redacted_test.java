// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: ../wire-runtime/src/test/proto/redacted_test.proto
package com.squareup.wire.protos.redacted;

import com.google.protobuf.FieldOptions;
import com.squareup.wire.Extension;
import java.lang.Boolean;

public final class Ext_redacted_test {
  public static final Extension<FieldOptions, Boolean> redacted = Extension
      .boolExtending(FieldOptions.class)
      .setName("squareup.protos.redacted_test.redacted")
      .setTag(22200)
      .buildOptional();

  private Ext_redacted_test() {
  }
}
