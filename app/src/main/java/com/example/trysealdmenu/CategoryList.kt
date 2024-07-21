package com.example.trysealdmenu

class CategoryList {
    fun test() {
        val showVisitReport = false
        CategoryBuilder.build {
            dairyCat {
                ItemBuilder.build {
                    milk {

                    }
                }
            }
        }
    }
}

class CategoryBuilder {
    private var list = ArrayList<Cat>()

    private fun addReport(cat: Cat) {
        list.add(cat)
    }

    companion object {
        inline fun build(builder: CategoryBuilder.() -> Unit) {
            CategoryBuilder().apply(builder)
        }
    }

    fun dairyCat(builder: ItemBuilder.() -> Unit): Cat {
        val cat = Cat("dairy")
        return cat.apply { addReport(cat) }.apply(builder)
    }
}

class ItemBuilder {
    private var list = ArrayList<Item>()

    fun addReport(item: Item) {
        list.add(item)
    }

    companion object {
        inline fun build(builder: ItemBuilder.() -> Unit) {
            ItemBuilder().apply(builder)
        }
    }

    fun milk(onClick: () -> Unit = {}) =
        Item("dairy", onClick).apply { addReport(this) }
}

data class Cat(
    val name: String,
) {
    fun apply(block: ItemBuilder.() -> Unit): Cat {
        return this
    }
}

data class Item(
    val name: String,
    val onClick: () -> Unit = {}
)