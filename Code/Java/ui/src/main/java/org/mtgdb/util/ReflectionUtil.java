package org.mtgdb.util;

import java.lang.reflect.*;
import java.util.*;


public final class ReflectionUtil {

  private ReflectionUtil() {}
  
  @SuppressWarnings("unchecked")
  public static <T> Class<T> getEnumClass(T obj) {
    Class<? extends Object> enumType = obj.getClass();
    if (!enumType.isEnum()) enumType = enumType.getSuperclass();
    if (enumType.isEnum()) return (Class<T>) enumType;
    throw new IllegalArgumentException("object was not an enum"); //$NON-NLS-1$
  }
  
  /**
   * Gibt einen Package-Namen als Java-Pfad ('/' als Separator) zurück.
   * Der Pfad hört dabei mit einem Slash auf.
   * 
   * @param clazz
   * @return
   */
  public static String getPackageNameAsJavaPath(final Class<?> clazz) {
    if (clazz == null) throw new IllegalArgumentException("clazz"); //$NON-NLS-1$
    return Constants.SLASH + clazz.getPackage().getName().replace(Constants.DOT, Constants.SLASH) + Constants.SLASH;
  }
  
  
  public static Map<String, Object> getStaticFieldData(final Class<?> clazz)  {
    final Map<String, Object> result = new HashMap<String, Object>();
    
    final Field[] declaredFields = clazz.getDeclaredFields();
    for(final Field field: declaredFields) {
      if (!Modifier.isStatic(field.getModifiers())) continue;
      field.setAccessible(true);
      
      putFieldValue(result, field);
      field.setAccessible(false);
    }
    return result;
  }
  
  /**
   * Gibt die Enum-Konstante mit dem angegebenen Namen zurück. Wird keine gefunden, wird null zurück gegeben.
   * 
   * @param <T>
   * @param enumType
   * @param name
   * @return
   */
  public static <T extends Enum<T>> T valueOf(final Class<T> enumType, final String name) {
    try {
      return Enum.valueOf(enumType, name);
    }
    catch (final IllegalArgumentException e) {
      return null;
    }
  }
  
  private static void putFieldValue(final Map<String, Object> result, final Field field) {
    try {
      final Object value = field.get(null);
      result.put(field.getName(), value);
    }
    catch (final Exception e) {
      throw new RuntimeException("Could not access field: " + field, e); //$NON-NLS-1$
    }
  }

  public static Object callGetter(final Object object, final String name) {
    try {
      Method method = object.getClass().getMethod(name, new Class[0]);
      Object result = method.invoke(object, new Object[0]);
      return result;
    }
    catch (Exception e) {
      throw new RuntimeException("error calling getter ",e);
    }
  }

  public static void callSetter(final Object object, final String name, final Object value) {
    try {
      Method method = object.getClass().getMethod(name, new Class[] {value.getClass()});
      method.invoke(object, new Object[] {value});
    }
    catch (Exception e) {
      throw new RuntimeException("error calling getter ",e);
    }
  }

  public static String getFieldValueAsString(final Object object, final String name) {
    try {
      return getField(object, name).get(object).toString();
    }
    catch (IllegalAccessException e) {
      throw new IllegalStateException("could not access field " + name);
    }
  }

  public static Field getField(final Object object, final String name) {
    try {
      return object.getClass().getField(name);
    }
    catch (NoSuchFieldException e) {
      throw new IllegalArgumentException("field not found " + name);
    }
  }

  public static void setFieldValue(final Object object, final String name, final Object value) {
    try {
      getField(object, name).set(object, value);
    } catch (IllegalAccessException e) {
      throw new IllegalStateException("could not access field " + name);
    }
  }
}
