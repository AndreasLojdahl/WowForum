<template>
  <div class="container" v-if="thread">
    <div class="row justify-content-center mt-5">
      <h1>{{ thread.title }}</h1>
    </div>

    <form v-if="user" @submit.prevent="createMessage" class="mb-4">
      <div class="form-group">
        <label for="message">Meddelande</label>
        <input
          type="text"
          class="form-control"
          id="message"
          required
          v-model="message.messageContent"
        />
      </div>
      <button type="submit" class="btn btn-dark">
        Skicka meddelandet
      </button>
    </form>

    <Message
      v-for="message in thread.messages"
      :key="message.message_id"
      :message="message"
    />
  </div>
</template>
<script>
import Message from "../components/Message.vue";
export default {
  name: "Thread",
  components: {
    Message,
  },
  computed: {
    thread() {
      return this.$store.state.thread;
    },
    user() {
      return this.$store.state.loggedInUser;
    },
  },
  data() {
    return {
      message: {
        messageContent: null,
      },
    };
  },
  methods: {
    async createMessage() {
      let messageToCreate = {
        messageContent: this.message.messageContent,
      };

      let newMessage = await fetch(
        `/api/v1/messages/${this.thread.thread_id}`,
        {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(messageToCreate),
        }
      );
      newMessage = await newMessage.json();
      console.log(newMessage);
    },
  },
  async created() {
    if (!this.thread) {
      await this.$store.dispatch("fetchThread", {
        forumId: this.$route.params.forum_id,
        threadId: this.$route.params.thread_id,
      });
    }
  },
};
</script>
<style scoped></style>
