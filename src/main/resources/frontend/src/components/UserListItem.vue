<template>
  <div class="card text-center container ">
    <div class="card-header row">
      <div class="col">
        <div class="row">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="20"
            height="20"
            fill="currentColor"
            class="bi bi-person-circle"
            viewBox="0 0 16 16"
          >
            <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z" />
            <path
              fill-rule="evenodd"
              d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"
            />
          </svg>
          <div class="col font-bold font-size-username text-left">
            {{ user.username }}
          </div>
        </div>
        <div class="col text-left font-oblique">{{ user.email }}</div>
      </div>
      <div class="col-xs-12 col-sm-12 col-md-3 col-lg-2 col-xl-2">
        <label
          v-if="
            !user.roles.includes('ADMIN') && !user.roles.includes('MODERATOR')
          "
          class="font-bold text-center full-width"
          >User
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="25"
            height="25"
            fill="currentColor"
            class="bi bi-check2-square col color-green icon text-center"
            viewBox="0 0 16 16"
          >
            <path
              d="M3 14.5A1.5 1.5 0 0 1 1.5 13V3A1.5 1.5 0 0 1 3 1.5h8a.5.5 0 0 1 0 1H3a.5.5 0 0 0-.5.5v10a.5.5 0 0 0 .5.5h10a.5.5 0 0 0 .5-.5V8a.5.5 0 0 1 1 0v5a1.5 1.5 0 0 1-1.5 1.5H3z"
            />
            <path
              d="M8.354 10.354l7-7a.5.5 0 0 0-.708-.708L8 9.293 5.354 6.646a.5.5 0 1 0-.708.708l3 3a.5.5 0 0 0 .708 0z"
            />
          </svg>
        </label>

        <label
          v-if="
            user.roles.includes('MODERATOR') && user.roles.includes('ADMIN')
              ? false
              : user.roles.includes('MODERATOR')
              ? true
              : false
          "
          class="font-bold text-center full-width"
          >Moderator
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="25"
            height="25"
            fill="currentColor"
            class="bi bi-check2-square col color-green icon"
            viewBox="0 0 16 16"
          >
            <path
              d="M3 14.5A1.5 1.5 0 0 1 1.5 13V3A1.5 1.5 0 0 1 3 1.5h8a.5.5 0 0 1 0 1H3a.5.5 0 0 0-.5.5v10a.5.5 0 0 0 .5.5h10a.5.5 0 0 0 .5-.5V8a.5.5 0 0 1 1 0v5a1.5 1.5 0 0 1-1.5 1.5H3z"
            />
            <path
              d="M8.354 10.354l7-7a.5.5 0 0 0-.708-.708L8 9.293 5.354 6.646a.5.5 0 1 0-.708.708l3 3a.5.5 0 0 0 .708 0z"
            />
          </svg>
        </label>

        <label
          v-if="user.roles.includes('ADMIN')"
          class="font-bold text-center full-width"
          >Admin
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="25"
            height="25"
            fill="currentColor"
            class="bi bi-check2-square col color-green icon text-center"
            viewBox="0 0 16 16"
          >
            <path
              d="M3 14.5A1.5 1.5 0 0 1 1.5 13V3A1.5 1.5 0 0 1 3 1.5h8a.5.5 0 0 1 0 1H3a.5.5 0 0 0-.5.5v10a.5.5 0 0 0 .5.5h10a.5.5 0 0 0 .5-.5V8a.5.5 0 0 1 1 0v5a1.5 1.5 0 0 1-1.5 1.5H3z"
            />
            <path
              d="M8.354 10.354l7-7a.5.5 0 0 0-.708-.708L8 9.293 5.354 6.646a.5.5 0 1 0-.708.708l3 3a.5.5 0 0 0 .708 0z"
            />
          </svg>
        </label>
      </div>
    </div>
    <div class="card-body">
      <div v-if="!user.roles.includes('ADMIN')" class="col">
        <div class="row">
          <div class="input-group mb-3 col-xs-12 col-sm-12 col-md-6 col-xl-6">
            <div class=" text-left font-bold col-12 mb-3">
              Lägg till för att moderera forum
            </div>

            <div class="input-group-prepend">
              <button
                class="btn btn-dark"
                type="button"
                @click="promoteModerator(selectedPromote)"
              >
                Lägg till
              </button>
            </div>
            <select
              v-on:change="setSelectedPromote($event)"
              class="custom-select"
              id="forumPromote"
            >
              <option selected>Välj...</option>
              <option
                :class="forum.name"
                :value="forum.forum_id"
                v-for="forum in moderatorForums"
                :key="forum.forum_id"
                >{{ forum.name }} forum</option
              >
            </select>
          </div>

          <div class="input-group mb-3 col-xs-12 col-sm-12 col-md-6 col-xl-6">
            <div class=" text-left font-bold col-12 mb-3">
              Ta bort för att omoderera forum
            </div>
            <div class="input-group-prepend">
              <button
                class="btn btn-dark"
                type="button"
                @click="demoteModerator(selectedDemote)"
              >
                Ta bort
              </button>
            </div>
            <select
              v-on:change="setSelectedDemote($event)"
              class="custom-select"
              id="forumDemote"
            >
              <option selected>Välj...</option>
              <option
                :class="forum.name"
                :value="forum.forum_id"
                v-for="forum in moderates"
                :key="forum.forum_id"
                >{{ forum.name }} forum</option
              >
            </select>
          </div>
        </div>
      </div>
    </div>
    <div
      class="card-footer text-muted row"
      v-if="!user.roles.includes('ADMIN')"
    >
      <svg
        @click="deleteUser()"
        xmlns="http://www.w3.org/2000/svg"
        width="25"
        height="25"
        fill="currentColor"
        class="bi bi-trash icon-trash mr-3"
        viewBox="0 0 16 16"
      >
        <path
          d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"
        />
        <path
          fill-rule="evenodd"
          d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"
        />
      </svg>
      <div>Ta bort användare</div>
    </div>
  </div>
</template>
<script>
export default {
  name: "UserListItem",
  props: {
    user: Object,
  },
  data() {
    return {
      selectedPromote: null,
      selectedDemote: null,
    };
  },
  computed: {
    forums() {
      return this.$store.state.forums;
    },
    moderates() {
      return this.forums.filter((forum) =>
        this.user.forumsToModerate.includes(forum.forum_id)
      );
    },
    moderatorForums() {
      return this.forums.filter(
        (forum) => !this.user.forumsToModerate.includes(forum.forum_id)
      );
    },
  },
  methods: {
    setSelectedPromote(e) {
      this.selectedPromote = e.target.value;
    },
    setSelectedDemote(e) {
      this.selectedDemote = e.target.value;
    },
    async deleteUser() {
      const response = await fetch(`/api/v1/users/${this.user.user_id}`, {
        method: "DELETE",
      });
      if (response.status === 204) {
        this.$emit("deletedUser");
      }
    },
    async promoteModerator(forumId) {
      let response = await fetch(
        `/api/v1/forums/${forumId}/moderators/${this.user.user_id}`,
        { method: "PUT" }
      );
      if (response.status === 204) {
        this.$store.dispatch("searchUsers", this.$store.state.searchedUsername);
      }
    },
    async demoteModerator(forumId) {
      let response = await fetch(
        `/api/v1/forums/${forumId}/moderators/${this.user.user_id}`,
        { method: "DELETE" }
      );
      if (response.status === 204) {
        this.$store.dispatch("searchUsers", this.$store.state.searchedUsername);
      }
    },
  },
};
</script>
<style scoped>
.font-bold {
  font-weight: bold;
}
.font-size-username {
  font-size: 1.3rem;
}
.font-oblique {
  font-style: oblique;
}
.color-green {
  color: green;
}
.icon-trash:hover {
  color: red;
  cursor: pointer;
}

.full-width {
  width: 100%;
}

.Warrior {
  background-color: #cebc98;
}
.Mage {
  background-color: #75dfff;
}
.Paladin {
  background-color: #fc98ff;
}
.Rogue {
  background-color: #f5c833;
}
.Druid {
  background-color: #f77f0f;
}
.Priest {
  background-color: #e6e2dc;
}
.Warlock {
  background-color: #a38aaa;
}
</style>
