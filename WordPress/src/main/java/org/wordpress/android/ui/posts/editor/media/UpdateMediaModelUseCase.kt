package org.wordpress.android.ui.posts.editor.media

import dagger.Reusable
import org.wordpress.android.fluxc.Dispatcher
import org.wordpress.android.fluxc.generated.MediaActionBuilder
import org.wordpress.android.fluxc.model.MediaModel
import org.wordpress.android.fluxc.model.MediaModel.MediaUploadState
import javax.inject.Inject

/**
 * Updates posts localId, remoteId and upload status.
 *
 */
@Reusable
class UpdateMediaModelUseCase @Inject constructor(private val dispatcher: Dispatcher) {
    fun updateMediaModel(
        media: MediaModel,
        postData: EditorMediaPostData,
        initialUploadState: MediaUploadState
    ) {
        setPostIds(media, postData)
        media.setUploadState(initialUploadState)
        dispatcher.dispatch(MediaActionBuilder.newUpdateMediaAction(media))
    }

    private fun setPostIds(
        media: MediaModel,
        postData: EditorMediaPostData
    ) {
        if (!postData.isLocalDraft) {
            media.postId = postData.remotePostId
        }
        media.localPostId = postData.localPostId
    }
}
