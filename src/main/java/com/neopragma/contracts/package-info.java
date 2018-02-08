/**
 * Supports precondition and postcondition checks
 * in the manner of Design by Contract.
 * <p>
 * Invariants are not directly supported.
 * <p>
 * To assert preconditions, call Contract.require(...) at the top of methods.
 * <p>
 * To assert postconditions, call Contract.ensure(...) at the end of methods (before the return statement, if any).
 * <p>
 * @since 1.8
 * @author neopragma
 */
package com.neopragma.contracts;