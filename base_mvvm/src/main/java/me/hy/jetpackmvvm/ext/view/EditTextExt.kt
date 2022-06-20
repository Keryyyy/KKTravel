@file:OptIn(ExperimentalContracts::class, ExperimentalCoroutinesApi::class, FlowPreview::class)
@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package me.hy.jetpackmvvm.ext.view

import android.text.Editable
import android.text.InputFilter
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.CheckResult
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doBeforeTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import me.hy.jetpackmvvm.ext.MoneyInputFilter
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.contracts.ExperimentalContracts

/**
 *
 * ViewEditTextEventExt 是对 [androidx.core.widget.TextView.kt] 的二次封装，有效的避免内存泄漏
 *
 */

@CheckResult
@kotlin.internal.InlineOnly
inline fun EditText.doAfterTextChangeFlow(): Flow<Editable?> = callbackFlow {
    val textChangedListener = doAfterTextChanged {
        safeOffer(it)
    }
    awaitClose {
        removeTextChangedListener(textChangedListener)
    }
}

@CheckResult
@kotlin.internal.InlineOnly
inline fun EditText.doBeforeTextChangeFlow(): Flow<CharSequence?> = callbackFlow {
    val textChangeListener = doBeforeTextChanged { text, start, count, after ->
        safeOffer(text)
    }
    awaitClose { removeTextChangedListener(textChangeListener) }
}

@CheckResult
@kotlin.internal.InlineOnly
inline fun EditText.doTextChangedFlow(): Flow<CharSequence?> = callbackFlow {
    val textChangeListener = doOnTextChanged { text, start, count, after ->
        safeOffer(text)
    }
    awaitClose { removeTextChangedListener(textChangeListener) }
}

/**
 * Example：
 *
 * editText.textChange(lifecycleScope) {
 *
 * }
 */
inline fun EditText.textChange(
    lifecycle: LifecycleCoroutineScope,
    crossinline onChange: (s: String) -> Unit
) {
    doAfterTextChangeFlow()
        .onEach {
            onChange(it.toString())
        }.launchIn(lifecycle)
}

/**
 * Example：
 *
 * editText.textChange(
 *      lifecycle = lifecycleScope,
 *      timeoutMillis = 500
 * ) {
 *
 * }
 */
inline fun EditText.textChange(
    lifecycle: LifecycleCoroutineScope,
    timeoutMillis: Long = 500,
    crossinline onChange: (s: String) -> Unit
) {
    doAfterTextChangeFlow()
        .debounce(timeoutMillis)
        .onEach {
            onChange(it.toString())
        }.launchIn(lifecycle)
}

/**
 * Example：
 *
 * editText.textChangeWithbefore(lifecycleScope) {
 *
 * }
 */
inline fun EditText.textChangeWithbefore(
    lifecycle: LifecycleCoroutineScope,
    crossinline onChange: (s: String) -> Unit
) {
    doBeforeTextChangeFlow()
        .onEach {
            onChange(it.toString())
        }.launchIn(lifecycle)
}

/**
 * Example：
 *
 * editText.textChangeWithbefore(
 *      lifecycle = lifecycleScope,
 *      timeoutMillis = 500
 * ) {
 *
 * }
 */
inline fun EditText.textChangeWithBefore(
    lifecycle: LifecycleCoroutineScope,
    timeoutMillis: Long = 500,
    crossinline onChange: (s: String) -> Unit
) {
    doBeforeTextChangeFlow()
        .debounce(timeoutMillis)
        .onEach {
            onChange(it.toString())
        }.launchIn(lifecycle)
}

/**
 * Example：
 *
 * editText.textChangeWithAfter(lifecycleScope) {
 *
 * }
 */
inline fun EditText.textChangeWithAfter(
    lifecycle: LifecycleCoroutineScope,
    crossinline onChange: (s: String) -> Unit
) {
    doAfterTextChangeFlow()
        .onEach {
            onChange(it.toString())
        }.launchIn(lifecycle)
}

/**
 * Example：
 *
 * editText.textChangeWithAfter(
 *      lifecycle = lifecycleScope,
 *      timeoutMillis = 500
 * ) {
 *
 * }
 */
inline fun EditText.textChangeWithAfter(
    lifecycle: LifecycleCoroutineScope,
    timeoutMillis: Long = 500,
    crossinline onChange: (s: String) -> Unit
) {
    doAfterTextChangeFlow()
        .debounce(timeoutMillis)
        .onEach {
            onChange(it.toString())
        }.launchIn(lifecycle)
}


/**
 * 获取文本
 */
@kotlin.internal.InlineOnly
inline fun EditText.textString(): String {
    return this.text.toString()
}
/**
 * 获取去除空字符串的文本
 */
@kotlin.internal.InlineOnly
inline fun EditText.textStringTrim(): String {
    return this.textString().trim()
}
/**
 * 文本是否为空
 */
@kotlin.internal.InlineOnly
inline fun EditText.isEmpty(): Boolean {
    return this.textString().isEmpty()
}

/**
 * 去空字符串后文本是否为空
 */
@kotlin.internal.InlineOnly
inline fun EditText.isTrimEmpty(): Boolean {
    return this.textStringTrim().isEmpty()
}
/**
 * 获取文本
 */
@kotlin.internal.InlineOnly
inline fun TextView.textString(): String {
    return this.text.toString()
}
/**
 * 获取去除空字符串的文本
 */
@kotlin.internal.InlineOnly
inline fun TextView.textStringTrim(): String {
    return this.textString().trim()
}
/**
 * 文本是否为空
 */
@kotlin.internal.InlineOnly
inline fun TextView.isEmpty(): Boolean {
    return this.textString().isEmpty()
}
/**
 * 去空字符串后文本是否为空
 */
@kotlin.internal.InlineOnly
inline fun TextView.isTrimEmpty(): Boolean {
    return this.textStringTrim().isEmpty()
}
/**
 *  设置EditText输入只能为价格格式
 */
@kotlin.internal.InlineOnly
inline fun EditText.setMoneyMode() {
    filters = arrayOf(MoneyInputFilter())
}

/**
 * 禁止EditText输入空格和换行符
 *
 * @param length 需要限制输入的长度
 */
@kotlin.internal.InlineOnly
inline fun EditText.setNoInputSpace(length: Int) {
    val filter = InputFilter { source, start, end, dest, dstart, dend ->
        if (source == " " || source.toString().contentEquals("\n")) {
            ""
        } else {
            null
        }
    }
    this.filters = arrayOf(filter, InputFilter.LengthFilter(length))
}
@kotlin.internal.InlineOnly
inline fun EditText.setNoInputSpace() {
    val filter = InputFilter { source, start, end, dest, dstart, dend ->
        if (source == " " || source.toString().contentEquals("\n")) {
            ""
        } else {
            null
        }
    }
    this.filters = arrayOf(filter)
}

/**
 * 禁止EditText输入特殊字符
 *
 * @param editText EditText输入框
 */
@kotlin.internal.InlineOnly
inline fun EditText.setEditTextInputNoSpecial(editText: EditText) {
    val filter = InputFilter { source, start, end, dest, dstart, dend ->
        val speChat =
            "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]"
        val pattern: Pattern = Pattern.compile(speChat)
        val matcher: Matcher = pattern.matcher(source.toString())
        if (matcher.find()) {
            ""
        } else {
            null
        }
    }
    editText.filters = arrayOf(filter)
}