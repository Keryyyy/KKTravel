package me.hy.jetpackmvvm.ext

/**
 * <pre>
 *
 *     author: Hy
 *     time  : 2022/03/25
 *     desc  :
 *
 * </pre>
 */

/**
 * Map不为空
 */
inline fun<K, V, R : Any> Map<K, V>.mapNotNullAndEmpty(notNull:((Map<K, V>) -> R?),itsNull:()->Unit = {}){
    if (!this.isNullOrEmpty()){
        notNull.invoke(this)
    }else{
        itsNull.invoke()
    }
}