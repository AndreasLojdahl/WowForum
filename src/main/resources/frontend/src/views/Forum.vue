<template>
  <div class="container">
    <div class="row justify-content-center mt-5">
      <h1 class="forum-header">{{ forum.name }}</h1>
    </div>

    <form @submit.prevent="createThread" class="mb-4">
      <div class="form-group">
        <label for="title">Titel</label>
        <input
          type="text"
          class="form-control"
          id="title"
          required
          v-model="thread.title"
        />
      </div>
       <div class="form-group">
        <label for="message">Meddelande</label>
        <input
          type="text"
          class="form-control"
          id="message"
          required
          v-model="thread.initialMessage"
        />
      </div>
    <button type="submit" class="btn btn-secondary">
      Skapa ny tråd
    </button>
    </form>

    <ForumListItem
      v-for="thread in forum.threads"
      :key="thread.id"
      :threadObj="thread"
      :forum="forum.name"
    />
  </div>
</template>

<script>
import ForumListItem from "../components/ForumListItem.vue";
// @ is an alias to /src

export default {
  name: "Forum",
  props: {},
  components: {
    ForumListItem,
  },
  data() {
    return {
      thread: {
        title: null,
        initialMessage: null
      }
    };
  },
  computed: {
    paramName() {
      return this.$route.params.forum;
    },
    forum() { 
      return this.$store.state.forum;
    },
  },
  methods: {
    async createThread(){
      let threadToCreate = {
        title: this.thread.title,
        initialMessage: this.thread.initialMessage
      }
      let newThread = await fetch(`/api/v1/forums/${this.forum.forum_id}/threads`, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(threadToCreate),
      })
      newThread = await newThread.json();
      console.log(newThread)
    }
  },
  watch: {
    paramName: function() {
      this.$store.dispatch("fetchForum", this.paramName);
    },
  },
  async created() {
    await this.$store.dispatch("fetchForum", this.paramName);
  },
};
</script>
<style></style>
