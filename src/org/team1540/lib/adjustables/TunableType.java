package org.team1540.lib.adjustables;

public enum TunableType {
  STRING(String.class), INT(Integer.TYPE), DOUBLE(Double.TYPE), BOOLEAN(Boolean.TYPE);

  final Class<?> cls;

  TunableType(Class<?> cls) {this.cls = cls;}
}
