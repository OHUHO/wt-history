<template>
    <button v-if="isLink && !variantButtonClass && !readMoreButton"
        :class="[
            'btn-default',
            size ? `btn-${size}` : '',
            outlined ? 'btn-border' : '',
            icon ? 'btn-icon' : ''
        ]">
        {{ title }}
        <Icon v-if="icon" :name="icon" size="20" class="icon"/>
    </button>
    <button v-else-if="!isLink && !variantButtonClass && !readMoreButton"
        type="button"
        :class="[
            'btn-default',
            size ? `btn-${size}` : '',
            outlined ? 'btn-border' : '',
            icon ? 'btn-icon' : ''
        ]">
        {{ title }}
        <Icon v-if="icon" :name="icon" size="20" class="icon"/>
    </button>
    <button v-else-if="isLink && variantButtonClass && !readMoreButton"
        :class="[
            'btn-default',
            size ? `btn-${size}` : '',
            outlined ? 'btn-border' : '',
            icon ? 'btn-icon' : '',
            variantButtonClass ? variantButtonClass : ''
        ]">
        <slot/>
    </button>
    <button v-else-if="!isLink && variantButtonClass && !readMoreButton"
        type="button"
        :class="[
            'btn-default',
            size ? `btn-${size}` : '',
            outlined ? 'btn-border' : '',
            icon ? 'btn-icon' : '',
            variantButtonClass ? variantButtonClass : ''
        ]">
        <slot/>
    </button>
    <button v-else-if="readMoreButton"
        :class="[
            'btn-read-more',
            size ? `btn-${size}` : '',
            outlined ? 'btn-border' : '',
            icon ? 'btn-icon' : '',
            variantButtonClass ? variantButtonClass : ''
        ]">
        <slot/>
    </button>
</template>

<script>
    import Icon from '../../icon/Icon';

    export default {
        name: 'Button',
        components: {Icon},
        props: {
            title: {
                type: String,
            },
            size: {
                type: String,
                default: null
            },
            outlined: {
                type: Boolean,
                default: false
            },
            icon: {
                type: String,
                default: null
            },
            isLink: {
                type: Boolean,
                default: true
            },
            variantButtonClass: {
                type: String,
                default: null
            },
            readMoreButton: {
                type: Boolean,
                default: false
            }
        }
    }
</script>
