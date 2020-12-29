<template>
    <div class="container">
        <h1>{{thread.title}}</h1>

    </div>
</template>
<script>
export default {

    computed: {
        thread(){
            return this.$store.state.thread
        }
    },
    created(){
        
        // let id = this.$route.params.id;
        // if(this.thread.thread_id !== id){

        // }
        // console.log(this.thread)
    }
    
}
</script>
<style scoped>

</style>