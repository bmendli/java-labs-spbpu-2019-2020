package lab3.builder;

import lab3.ExecuteException;
import lab3.methods.AbstractMethodsOwnStringBuilder;
import lab3.methods.Append;
import lab3.methods.AppendCharArray;
import lab3.methods.AppendCharSequence;
import lab3.methods.AppendCodePoint;
import lab3.methods.Delete;
import lab3.methods.Insert;
import lab3.methods.InsertCharArray;
import lab3.methods.InsertCharSequence;
import lab3.methods.Replace;
import lab3.methods.Reverse;

import java.util.ArrayDeque;
import java.util.Deque;

public final class OwnStringBuilder implements CharSequence {

    private StringBuilder stringBuilder;
    private Deque<AbstractMethodsOwnStringBuilder> executedMethods;

    public OwnStringBuilder() {
        executedMethods = new ArrayDeque<>();
        stringBuilder = new StringBuilder();
    }

    public OwnStringBuilder(CharSequence seq) {
        executedMethods = new ArrayDeque<>();
        stringBuilder = new StringBuilder(seq);
    }

    public OwnStringBuilder(int capacity) {
        executedMethods = new ArrayDeque<>();
        stringBuilder = new StringBuilder(capacity);
    }

    public OwnStringBuilder(String str) {
        executedMethods = new ArrayDeque<>();
        stringBuilder = new StringBuilder(str);
    }

    public OwnStringBuilder append(boolean b) throws ExecuteException {
        Append<Boolean> method = new Append<>(stringBuilder, b);
        method.execute();
        executedMethods.push(method);
        return this;
    }

    public OwnStringBuilder append(char c) throws ExecuteException {
        Append<Character> method = new Append<>(stringBuilder, c);
        method.execute();
        executedMethods.push(method);
        return this;
    }

    public OwnStringBuilder append(char[] str) throws ExecuteException {
        AppendCharArray method = new AppendCharArray(stringBuilder, str, 0, str.length);
        method.execute();
        executedMethods.push(method);
        return this;
    }

    public OwnStringBuilder append(char[] str, int offset, int len) throws ExecuteException {
        AppendCharArray method = new AppendCharArray(stringBuilder, str, offset, len);
        method.execute();
        executedMethods.push(method);
        return this;
    }

    public OwnStringBuilder append(CharSequence s) throws ExecuteException {
        AppendCharSequence method = new AppendCharSequence(stringBuilder, s, 0, s.length());
        method.execute();
        executedMethods.push(method);
        return this;
    }

    public OwnStringBuilder append(CharSequence s, int start, int end) throws ExecuteException {
        AppendCharSequence method = new AppendCharSequence(stringBuilder, s, start, end);
        method.execute();
        executedMethods.push(method);
        return this;
    }

    public OwnStringBuilder append(double d) throws ExecuteException {
        Append<Double> method = new Append<>(stringBuilder, d);
        method.execute();
        executedMethods.push(method);
        return this;
    }

    public OwnStringBuilder append(float f) throws ExecuteException {
        Append<Float> method = new Append<>(stringBuilder, f);
        method.execute();
        executedMethods.push(method);
        return this;
    }

    public OwnStringBuilder append(int i) throws ExecuteException {
        Append<Integer> method = new Append<>(stringBuilder, i);
        method.execute();
        executedMethods.push(method);
        return this;
    }

    public OwnStringBuilder append(long lng) throws ExecuteException {
        Append<Long> method = new Append<>(stringBuilder, lng);
        method.execute();
        executedMethods.push(method);
        return this;
    }

    public OwnStringBuilder append(Object obj) throws ExecuteException {
        Append<Object> method = new Append<>(stringBuilder, obj);
        method.execute();
        executedMethods.push(method);
        return this;
    }

    public OwnStringBuilder append(String str) throws ExecuteException {
        Append<String> method = new Append<>(stringBuilder, str);
        method.execute();
        executedMethods.push(method);
        return this;
    }

    public OwnStringBuilder append(StringBuffer sb) throws ExecuteException {
        Append<StringBuffer> method = new Append<>(stringBuilder, sb);
        method.execute();
        executedMethods.push(method);
        return this;
    }

    public OwnStringBuilder appendCodePoint(int codePoint) throws ExecuteException {
        AppendCodePoint method = new AppendCodePoint(stringBuilder, codePoint);
        method.execute();
        executedMethods.push(method);
        return this;
    }

    public int capacity() {
        return stringBuilder.capacity();
    }

    @Override
    public char charAt(int index) {
        return stringBuilder.charAt(index);
    }

    public int codePointAt(int index) {
        return stringBuilder.codePointAt(index);
    }

    public int codePointBefore(int index) {
        return stringBuilder.codePointBefore(index);
    }

    public int codePointCount(int beginIndex, int endIndex) {
        return stringBuilder.codePointCount(beginIndex, endIndex);
    }

    public OwnStringBuilder delete(int start, int end) throws ExecuteException {
        Delete method = new Delete(stringBuilder, start, end);
        method.execute();
        executedMethods.push(method);
        return this;
    }

    public OwnStringBuilder deleteCharAt(int index) throws ExecuteException {
        Delete method = new Delete(stringBuilder, index, index + 1);
        method.execute();
        executedMethods.push(method);
        return this;
    }

    public void ensureCapacity(int minimumCapacity) {
        stringBuilder.ensureCapacity(minimumCapacity);
    }

    public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) {
        stringBuilder.getChars(srcBegin, srcEnd, dst, dstBegin);
    }

    public int indexOf(String str) {
        return stringBuilder.indexOf(str);
    }

    public int indexOf(String str, int fromIndex) {
        return stringBuilder.indexOf(str, fromIndex);
    }

    public OwnStringBuilder insert(int offset, boolean b) throws ExecuteException {
        Insert<Boolean> method = new Insert<>(stringBuilder, offset, b);
        method.execute();
        executedMethods.push(method);
        return this;
    }

    public OwnStringBuilder insert(int offset, char c) throws ExecuteException {
        Insert<Character> method = new Insert<>(stringBuilder, offset, c);
        method.execute();
        executedMethods.push(method);
        return this;
    }

    public OwnStringBuilder insert(int offset, char[] str) throws ExecuteException {
        InsertCharArray method = new InsertCharArray(stringBuilder, offset, str, 0, str.length);
        method.execute();
        executedMethods.push(method);
        return this;
    }

    public OwnStringBuilder insert(int index, char[] str, int offset, int len) throws ExecuteException {
        InsertCharArray method = new InsertCharArray(stringBuilder, index, str, offset, len);
        method.execute();
        executedMethods.push(method);
        return this;
    }

    public OwnStringBuilder insert(int dstOffset, CharSequence s) throws ExecuteException {
        InsertCharSequence method = new InsertCharSequence(stringBuilder, dstOffset, s, 0, s.length());
        method.execute();
        executedMethods.push(method);
        return this;
    }

    public OwnStringBuilder insert(int dstOffset, CharSequence s, int start, int end) throws ExecuteException {
        InsertCharSequence method = new InsertCharSequence(stringBuilder, dstOffset, s, start, end);
        method.execute();
        executedMethods.push(method);
        return this;
    }

    public OwnStringBuilder insert(int offset, double d) throws ExecuteException {
        Insert<Double> method = new Insert<>(stringBuilder, offset, d);
        method.execute();
        executedMethods.push(method);
        return this;
    }

    public OwnStringBuilder insert(int offset, float f) throws ExecuteException {
        Insert<Float> method = new Insert<>(stringBuilder, offset, f);
        method.execute();
        executedMethods.push(method);
        return this;
    }

    public OwnStringBuilder insert(int offset, int i) throws ExecuteException {
        Insert<Integer> method = new Insert<>(stringBuilder, offset, i);
        method.execute();
        executedMethods.push(method);
        return this;
    }

    public OwnStringBuilder insert(int offset, long l) throws ExecuteException {
        Insert<Long> method = new Insert<>(stringBuilder, offset, l);
        method.execute();
        executedMethods.push(method);
        return this;
    }

    public OwnStringBuilder insert(int offset, Object obj) throws ExecuteException {
        Insert<Object> method = new Insert<>(stringBuilder, offset, obj);
        method.execute();
        executedMethods.push(method);
        return this;
    }

    public OwnStringBuilder insert(int offset, String str) throws ExecuteException {
        Insert<String> method = new Insert<>(stringBuilder, offset, str);
        method.execute();
        executedMethods.push(method);
        return this;
    }

    public int lastIndexOf(String str) {
        return stringBuilder.lastIndexOf(str);
    }

    public int lastIndexOf(String str, int fromIndex) {
        return stringBuilder.lastIndexOf(str, fromIndex);
    }

    @Override
    public int length() {
        return stringBuilder.length();
    }

    public int offsetByCodePoints(int index, int codePointOffset) {
        return stringBuilder.offsetByCodePoints(index, codePointOffset);
    }

    public OwnStringBuilder replace(int start, int end, String str) throws ExecuteException {
        Replace method = new Replace(stringBuilder, start, end, str);
        method.execute();
        executedMethods.push(method);
        return this;
    }

    public OwnStringBuilder reverse() throws ExecuteException {
        Reverse method = new Reverse(stringBuilder);
        method.execute();
        executedMethods.push(method);
        return this;
    }

    public void setCharAt(int index, char ch) {
        stringBuilder.setCharAt(index, ch);
    }

    public void setLength(int newLength) {
        stringBuilder.setLength(newLength);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return stringBuilder.subSequence(start, end);
    }

    public String substring(int start) {
        return stringBuilder.substring(start);
    }

    public String substring(int start, int end) {
        return stringBuilder.substring(start, end);
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }

    public void trimToSize() {
        stringBuilder.trimToSize();
    }

    public void undo() throws Exception {
        if (executedMethods.isEmpty()) {
            throw new Exception("No methods were called");
        }
        AbstractMethodsOwnStringBuilder amosb = executedMethods.pop();
        amosb.undo();
    }

    public void clear() {
        executedMethods.clear();
        stringBuilder.delete(0, stringBuilder.length());
    }
}
