package com.dagger.sample2.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Defines an qualifier annotation which can be used in conjunction with a type to identify dependencies within
 * this module's object graph.
 *
 * @see <a href="http://square.github.io/dagger/">the dagger documentation</a>
 */
@Qualifier
@Target({FIELD, PARAMETER, METHOD})
@Documented
@Retention(RUNTIME)
public @interface ApplicationQualifier {
}